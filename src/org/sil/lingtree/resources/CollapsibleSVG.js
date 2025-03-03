
// LingTree-specific attributes
var ltCollapsed = "lt:collapsed";
var ltCollapsedLines = "lt:collapsedLines";
var ltCollapsedNodes = "lt:collapsedNodes";
var ltDaughters = "lt:daughters";
var ltHeight = "lt:height";
var ltNodeTextItems = "lt:nodeTextItems";
var ltIsTriangle = "lt:isTriangle";
var ltMaxInColumnMothersWidth = "lt:maxInColumnMothersWidth";
var ltMaxWidthInColumn = "lt:maxWidthInColumn";
var ltMaxWidthOfDaughters = "lt:maxWidthOfDaughters";
var ltMother = "lt:mother";
var ltRightSister = "lt:rightSister";
var ltSubscript = "lt:subscript";
var ltSuperscript = "lt:superscript";
var ltWidth = "lt:width";
var ltXMid = "lt:xMid";
// SVG attributes
var svgId = "id";
var svgX1 = "x1";
var svgX2 = "x2";
var svgXCoord = "x";

// Code for handling collapsing nodes
function ProcessCollapsibleNode(nodeId) {
	var rootNode = document.getElementById("node0");
//	showNodeValues(rootNode);
	var text = document.getElementById(nodeId);
	var collapsedNodes = text.getAttribute(ltCollapsedNodes);
	let id = nodeId.substring(4);
	var nodes = collapsedNodes.split(',');

	var collapsedLines = text.getAttribute(ltCollapsedLines);
	var lines = collapsedLines.split(',');

	var isNodeCollapsed = text.getAttribute(ltCollapsed);
	if (isNodeCollapsed == 'true') {
		setVisibilityOfNodesAndLines(nodes, lines, 'visible');
		setVisibilityOfCollapsedTo(id, 'hidden');
		text.setAttribute(ltCollapsed, 'false');
	} else {
		setVisibilityOfNodesAndLines(nodes, lines, 'hidden');
		setVisibilityOfCollapsedTo(id, 'visible');
		text.setAttribute(ltCollapsed, 'true');
		hideAnyLowerCollapsedItems(nodes, lines);
	}
	calculateMaxWidthOfNodes(rootNode);
	if (isRightToLeft) {
		var dMaxWidthInColumn = parseFloat(rootNode.getAttribute(ltMaxWidthInColumn));
		var dWidth = parseFloat(rootNode.getAttribute(ltWidth));
		var dWidthToUse = Math.max(dWidth, dMaxWidthInColumn);
		calculateXCoordinateAndXMidOfNodes(rootNode, dWidthToUse + initialXCoord);
	} else {
		calculateXCoordinateAndXMidOfNodes(rootNode, initialXCoord);
	}
	resetAllLines(rootNode);
//	showNodeValues(rootNode);
}
function resetAllLines(node) {
	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	for (var i = 0; i < daughters.length - 1; i++) {
		var daughter = document.getElementById(daughters[i]);
		if (daughter == null) {
			continue;
		}
		var motherId = node.getAttribute(svgId);
		var fIsTriangle = daughter.getAttribute(ltIsTriangle);
		if (fIsTriangle == null || fIsTriangle == "false") {
			var dXMid = parseFloat(daughter.getAttribute(ltXMid));
			adjustLineBetweenNodeAndItsMother(daughters[i], dXMid, motherId);
		} else {
			adjustTriangleBetweenNodeAndItsMother(daughter, motherId);
		}
		resetAllLines(daughter);
	}
}
function setVisibilityOfNodesAndLines(nodes, lines, visibility) {
	for (let i = 0; i < nodes.length; i++) {
		var node = document.getElementById(nodes[i]);
		if (node != null) {
			node.setAttribute('visibility', visibility);
		} else {
			alert("nodes[i] not found: " + nodes[i]);
		}
	}
	for (let i = 0; i < lines.length; i++) {
		var line = document.getElementById(lines[i]);
		if (line != null) {
			line.setAttribute('visibility', visibility);
		} else {
			alert("lines[i] not found: " + lines[i]);
		}
	}
}
function setVisibilityOfCollapsedTo(id, visibility) {
	document.getElementById('collapsed' + id).setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T1').setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T2').setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T3').setAttribute('visibility', visibility);
}
function hideAnyLowerCollapsedItems(nodes, lines) {
	for (let i = 0; i < nodes.length; i++) {
		let id = nodes[i].substring(4);
		var node = document.getElementById(nodes[i]);
		if (node != null) {
			node.setAttribute(ltCollapsed, 'false');
		}
		node = document.getElementById('collapsed' + id);
		if (node != null) {
			node.setAttribute('visibility', 'hidden');
		}
		var lineId = 'line' + id;
		hideCollapsedTriangleLine(lineId, "T1");
		hideCollapsedTriangleLine(lineId, "T2");
		hideCollapsedTriangleLine(lineId, "T3");
	}
}
function hideCollapsedTriangleLine(lineId, trianglePart) {
	var line = document.getElementById(lineId + trianglePart);
	if (line != null) {
		line.setAttribute('visibility', 'hidden');
	}
}

// Code for recalculating x-coordinates

// The revised algorithm calculates the maximum width of a node in its column
function calculateMaxWidthOfNodes(node) {
	var dWidth = parseFloat(node.getAttribute(ltWidth));
	dWidth += calculateWidthOfSubOrSuperscript(node);
	var isNodeCollapsed = node.getAttribute(ltCollapsed);
	if (isNodeCollapsed == "true") {
		var dMax = Math.max(dWidth, dEllipsisWidth);
		node.setAttribute(ltMaxWidthInColumn, dMax);
		node.setAttribute(ltMaxWidthOfDaughters, dMax);
		return dMax;
	}
	var dMaxWidthOfNode = dWidth;
	var dMaxWidthOfDaughters = 0.0;
	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	var numDaughters = daughters.length - 1;
	for (var i = 0; i < numDaughters; i++) {
		var daughter = document.getElementById(daughters[i]);
		if (daughter == null) {
			continue;
		}
		dMaxWidthOfDaughters += calculateMaxWidthOfNodes(daughter);
		var rightSister = daughter.getAttribute(ltRightSister);
		if (rightSister != "node") {
			dMaxWidthOfDaughters += horizontalGap;
		}
	}
	dMaxWidthOfNode = Math.max(dMaxWidthOfNode, dMaxWidthOfDaughters);
	node.setAttribute(ltMaxWidthInColumn, dMaxWidthOfNode);
	node.setAttribute(ltMaxWidthOfDaughters, dMaxWidthOfDaughters);
	if (dMaxWidthOfDaughters < dWidth && numDaughters == 1) {
		var daughter = document.getElementById(daughters[0]);
		if (daughter != null) {
			setDaughtersMaxWidth(daughter, dWidth);
		}
	}
	return dMaxWidthOfNode;
}
function calculateWidthOfSubOrSuperscript(node) {
	var dMaxWidth = 0.0;
	var dSubWidth = 0.0;
	var dSuperWidth = 0.0;
	var sub = node.getAttribute(ltSubscript);
	if (sub != null) {
		var subscriptNode = document.getElementById(sub);
		if (subscriptNode != null) {
			dSubWidth = parseFloat(subscriptNode.getAttribute(ltWidth));
		}
	}
	var sup = node.getAttribute(ltSuperscript);
	if (sup != null) {
		var superscriptNode = document.getElementById(sup);
		if (superscriptNode != null) {
			dSuperWidth = parseFloat(superscriptNode.getAttribute(ltWidth));
		}
	}
	dMaxWidth = Math.max(dSubWidth, dSuperWidth);
	return dMaxWidth;
}
// When a higher node's width is wider than any of its daughters' width in its column,
// we need to set the maximum width of the daughters to that node's maximum width
// so the x-coordinate and x-mid values are correct.
function setDaughtersMaxWidth(node, newMaxWidth) {
	node.setAttribute(ltMaxWidthInColumn, newMaxWidth);
	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	var numDaughters = daughters.length - 1;
	if (numDaughters == 1) {
		var daughter = document.getElementById(daughters[0]);
		if (daughter != null) {
			setDaughtersMaxWidth(daughter, newMaxWidth);
		}
	}
}

// We use the maximum width of a node in its column (and its daughter's width) to
// set the x-coordinate and x-mid values.
// The left offset value needs to be maintained for the x-coordinate of where the column begins.
// For right-to-left, we use subrraction to go from right edge to left
function calculateXCoordinateAndXMidOfNodes(node, leftOffset) {
	if (node == null) {
		return;
	}
	var daughtersLeftOffset = leftOffset;
	var maxWidthInColumn = parseFloat(node.getAttribute(ltMaxWidthInColumn));
	var maxWidthOfDaughters = parseFloat(node.getAttribute(ltMaxWidthOfDaughters));
	var isNodeCollapsed = node.getAttribute(ltCollapsed);
	if (isNodeCollapsed == "true") {
		node.setAttribute(ltMaxWidthOfDaughters, dEllipsisWidth);
		var motherId = node.getAttribute(ltMother);
		var dMaxInlineMothersWidth = 0.0;
		var mother = document.getElementById(motherId);
		if (mother != null) {
			var mothersDaughtersList = mother.getAttribute(ltDaughters);
			var mothersDaughters = mothersDaughtersList.split(',');
			if (mothersDaughters.length == 2) {
				dMaxInlineMothersWidth = parseFloat(node.getAttribute(ltMaxInColumnMothersWidth));
			}
		}
		var dNodesWidth = parseFloat(node.getAttribute(ltWidth));
		var dSubOrSuperscriptWidth = calculateWidthOfSubOrSuperscript(node);
		var dWidthToUse = dNodesWidth + dSubOrSuperscriptWidth;
		var dMaxWidthInColumn = Math.max(dWidthToUse, dEllipsisWidth, dMaxInlineMothersWidth);
		node.setAttribute(ltMaxWidthInColumn, dMaxWidthInColumn);
		var xcoord = leftOffset;
		if (isRightToLeft) {
			xcoord -= (dMaxWidthInColumn + dWidthToUse) / 2;
		} else {
			xcoord += (dMaxWidthInColumn - dWidthToUse) / 2;
		}
		node.setAttribute(svgXCoord, xcoord);
		var dXMid = xcoord + (dWidthToUse / 2);
		node.setAttribute(ltXMid, dXMid);
		var nodeId = node.getAttribute(svgId);
		adjustEllipsisTriangleAndTextLocation(nodeId, dXMid);
		adjustLineBetweenNodeAndItsMother(nodeId, dXMid, motherId);
		if (dSubOrSuperscriptWidth > 0.0) {
			adjustAnySubOrSuperscript(node, xcoord + dNodesWidth);
		}
		adjustAnyNodeTextItems(node, xcoord);
		return;
	}

	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	var numDaughters = daughters.length - 1;
	if (maxWidthInColumn > maxWidthOfDaughters && numDaughters > 1) {
		daughtersLeftOffset += (maxWidthInColumn - maxWidthOfDaughters) / 2;
	}
	for (let i = 0; i < numDaughters; i++) {
		var daughterId = daughters[i];
		var daughter = document.getElementById(daughterId);
		if (daughter == null) {
			continue;
		}
		var gap = horizontalGap;
		if (i == 0 || nodeTextList == "true") {
			gap = 0.0;
		}
		var newLeftOffset = daughtersLeftOffset + gap;
		if (isRightToLeft) {
			newLeftOffset = daughtersLeftOffset - gap;
		}
		calculateXCoordinateAndXMidOfNodes(daughter, newLeftOffset);
		var daughtersMaxWidthInColumn = parseFloat(daughter.getAttribute(ltMaxWidthInColumn));
		if (isRightToLeft) {
			daughtersLeftOffset -= (daughtersMaxWidthInColumn + gap);
		} else {
			daughtersLeftOffset += daughtersMaxWidthInColumn + gap;
		}
	}
	var dXMid = calculateXMidOfNode(maxWidthInColumn, daughters, leftOffset);
	node.setAttribute(ltXMid, dXMid);
	var dNodesWidth = parseFloat(node.getAttribute(ltWidth));
	var dSubOrSuperscriptWidth = calculateWidthOfSubOrSuperscript(node);
	var dWidthToUse = dNodesWidth + dSubOrSuperscriptWidth;
	var xcoord = dXMid - (dWidthToUse / 2);
	node.setAttribute(svgXCoord, xcoord);
	if (dSubOrSuperscriptWidth > 0.0) {
		adjustAnySubOrSuperscript(node, xcoord + dNodesWidth);
	}
	var nodeTextList = node.getAttribute(ltNodeTextItems);
	adjustAnyNodeTextItems(node, xcoord);
}
function adjustAnySubOrSuperscript(node, xcoord) {
	var sub = node.getAttribute(ltSubscript);
	if (sub != null) {
		var subscriptNode = document.getElementById(sub);
		if (subscriptNode != null) {
			subscriptNode.setAttribute(svgXCoord, xcoord);
		}
	}
	var sup = node.getAttribute(ltSuperscript);
	if (sup != null) {
		var superscriptNode = document.getElementById(sup);
		if (superscriptNode != null) {
			superscriptNode.setAttribute(svgXCoord, xcoord);
		}
	}
}
function adjustAnyNodeTextItems(node, xcoord) {
	var nodeTextList = node.getAttribute(ltNodeTextItems);
	if (nodeTextList != null) {
		var dNodesXCoord = xcoord;
		var nodeTexts = nodeTextList.split(',');
		var numNodeTexts = nodeTexts.length - 1;
		for (let j = 0; j < numNodeTexts; j++) {
			var nodeText = document.getElementById(nodeTexts[j]);
			if (nodeText != null) {
				nodeText.setAttribute(svgXCoord, dNodesXCoord);
				var dntWidth = parseFloat(nodeText.getAttribute(ltWidth));
				dNodesXCoord += dntWidth;
			}
		}
	}
}
function adjustEllipsisTriangleAndTextLocation(nodeId, dXMid) {
	var dLeftmostX = dXMid - dEllipsisXOffset;
	var dRightmostX = dXMid + dEllipsisXOffset;
	var dTopX = dXMid;
	var id = nodeId.substring(4);
	// right part of line
	adjustCollapsedTriangle(dLeftmostX, dTopX, id, "1");
	// left part of line
	adjustCollapsedTriangle(dTopX, dRightmostX, id, "2");
	// bottom part of line
	adjustCollapsedTriangle(dLeftmostX, dRightmostX, id, "3");
	// collapsed text
	adjustCollapsedText(id, dLeftmostX);
}
function adjustCollapsedTriangle(dX1, dX2, nodeId, triangleId) {
	var line = document.getElementById("line" + nodeId + "T" + triangleId);
	if (line != null) {
		line.setAttribute(svgX1, dX1);
		line.setAttribute(svgX2, dX2);
	}
}
function adjustLineBetweenNodeAndItsMother(nodeId, dXMid, motherId) {
	var mother = document.getElementById(motherId);
	if (mother != null) {
		var dMotherXMid = parseFloat(mother.getAttribute(ltXMid));
		var line = document.getElementById("line" + nodeId.substring(4) + "-" + motherId.substring(4));
		if (line != null) {
			line.setAttribute(svgX1, dMotherXMid);
			line.setAttribute(svgX2, dXMid);
		}
	}
}
function adjustTriangleBetweenNodeAndItsMother(node, motherId) {
	var mother = document.getElementById(motherId);
	if (mother != null) {
		var nodeId = node.getAttribute(svgId);
		var dMotherXMid = parseFloat(mother.getAttribute(ltXMid));
		var dXCoord = parseFloat(node.getAttribute(svgXCoord));
		var dXWidth = parseFloat(node.getAttribute(ltWidth));
		var dX1 = dXCoord + dTriangleXOffset;
		var dX2 = dXCoord + dXWidth- dTriangleXOffset;
		var lineId = "line" + nodeId.substring(4) + "-" + motherId.substring(4);
		adjustTriangleLineXCoords(lineId, dX1, dMotherXMid);
		adjustTriangleLineXCoords(lineId + "T2", dMotherXMid, dX2);
		adjustTriangleLineXCoords(lineId + "T3", dX1, dX2);
	}
}
function adjustTriangleLineXCoords(lineId, dX1, dX2) {
	var line = document.getElementById(lineId);
	if (line != null) {
		line.setAttribute(svgX1, dX1);
		line.setAttribute(svgX2, dX2);
	}
}
function adjustCollapsedText(nodeId, dX) {
	var text = document.getElementById("collapsed" + nodeId);
	if (text != null) {
		text.setAttribute(svgXCoord, dX);
	}
}
function calculateXMidOfNode(maxWidthInColumn, daughters, leftOffset) {
	var dXMid = leftOffset + (maxWidthInColumn / 2);
	if (isRightToLeft) {
		dXMid = leftOffset - (maxWidthInColumn / 2);
	}
	var numDaughters = daughters.length - 1;
	if (isCenterColumnOrientedOnDaughtersWidth && numDaughters > 0) {
		var firstDaughter = document.getElementById(daughters[0]);
		if (firstDaughter != null) {
			var dFirstDaughterXMid = parseFloat(firstDaughter.getAttribute(ltXMid));
			if (numDaughters > 1) {
				var lastDaughter = document.getElementById(daughters[numDaughters - 1]);
				if (lastDaughter != null) {
					var dLastDaughterXMid = parseFloat(lastDaughter.getAttribute(ltXMid));
					var dWidthToUse = dLastDaughterXMid - dFirstDaughterXMid;
					var result = dFirstDaughterXMid + (dWidthToUse / 2);
					dXMid = result;
				}
			} else if (numDaughters == 1) {
				dXMid = dFirstDaughterXMid;
			}
		}
	}
	return dXMid;
}
function showNodeValues(node) {
	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	for (var i = 0; i < daughters.length - 1; i++) {
		var daughter = document.getElementById(daughters[i]);
		if (daughter == null) {
			continue;
		}
		showNodeValues(daughter);
	}
	var xcoord = parseFloat(node.getAttribute(svgXCoord));
	var dXMid = parseFloat(node.getAttribute(ltXMid));
	var width = parseFloat(node.getAttribute(ltWidth));
	var maxWidthInColumn = parseFloat(node.getAttribute(ltMaxWidthInColumn));
	console.log("node = "+ node.getAttribute(svgId) + "; " + node.innerHTML);
	console.log("\tx-coord   = " + xcoord);
	console.log("\txmid      = " + dXMid);
	console.log("\twidth     = " + width);
	console.log("\tmax       = " + maxWidthInColumn);
}

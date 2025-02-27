/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
/**
 * Collaspible SVG javascript code
 */

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
	console.log("before recalculations");
	showNodeValues(rootNode);
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
//		console.log("max in column = " + dMaxWidthInColumn);
//		console.log("width         = " + dWidth);
//		console.log("width to use  = " + dWidthToUse);
		calculateXCoordinateAndXMidOfNodes(rootNode, dWidthToUse + initialXCoord);
//				var dAdjust = svgWidth + initialXCoord;
//				console.log("dAdjust = " + dAdjust);
//				adjustForRightToLeftOrientation(rootNode, dAdjust);
	} else {
		calculateXCoordinateAndXMidOfNodes(rootNode, initialXCoord);
	}
	resetAllLines(rootNode);
	console.log("after recalculations");
	showNodeValues(rootNode);
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
	//	alert("collapsed: id = " + id + "; visibility = " + visibility);
	document.getElementById('collapsed' + id).setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T1').setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T2').setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T3').setAttribute('visibility', visibility);
}
function hideAnyLowerCollapsedItems(nodes, lines) {
	//	alert("hide any");
	for (let i = 0; i < nodes.length; i++) {
		//		alert("node " + nodes[i] + " to " + visibility);
		let id = nodes[i].substring(4);
		var node = document.getElementById(nodes[i]);
		if (node != null) {
			node.setAttribute(ltCollapsed, 'false');
		}
		node = document.getElementById('collapsed' + id);
		if (node != null) {
			//			alert("hiding " + 'collapsed' + id);
			node.setAttribute('visibility', 'hidden');
			//			text.setAttribute(ltCollapsed, 'false');
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
		//		alert("hiding " + lineId + trianglePart);
		line.setAttribute('visibility', 'hidden');
	}
}

// Code for recalculating x-coordinates

// The revised algorithm calculates the maximum width of a node in its column
function calculateMaxWidthOfNodes(node) {
	var dWidth = parseFloat(node.getAttribute(ltWidth));
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
		var dWidth = parseFloat(node.getAttribute(ltWidth));
		var dMaxWidthInColumn = Math.max(dWidth, dEllipsisWidth, dMaxInlineMothersWidth);
		node.setAttribute(ltMaxWidthInColumn, dMaxWidthInColumn);
		var xcoord = leftOffset;
		if (isRightToLeft) {
			xcoord -= (dMaxWidthInColumn + dWidth) / 2;
		} else {
			xcoord += (dMaxWidthInColumn - dWidth) / 2;
		}
		node.setAttribute(svgXCoord, xcoord);
		var dXMid = xcoord + (dWidth / 2);
		node.setAttribute(ltXMid, dXMid);
		var nodeId = node.getAttribute(svgId);
		adjustEllipsisTriangleAndTextLocation(nodeId, dXMid);
		adjustLineBetweenNodeAndItsMother(nodeId, dXMid, motherId);
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
		var nodeTextList = daughter.getAttribute(ltNodeTextItems);
		if (nodeTextList != null) {
			var nodeTexts = nodeTextList.split(',');
			var numNodeTexts = nodeTexts.length - 1;
			var nodeProperXCoord = daughter.getAttribute(svgXCoord);
			for (let j = 0; j < numNodeTexts; j++) {
				var nodeText = document.getElementById(nodeTexts[j]);
				if (nodeText != null) {
//					nodeText.setAttribute(svgXCoord, nodeProperXCoord);
					var dntWidth = nodeText.getAttribute(ltWidth);
					nodeProperXCoord += dntWidth;
				}
			}
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
	var width = parseFloat(node.getAttribute(ltWidth));
	var xcoord = dXMid - (width / 2);
	node.setAttribute(svgXCoord, xcoord);
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
//	console.log("node = "+ node.getAttribute(svgId) + "; " + node.innerHTML);
//	console.log("\tx-coord   = " + xcoord);
//	console.log("\txmid      = " + dXMid);
//	console.log("\twidth     = " + width);
//	console.log("\tmax       = " + maxWidthInColumn);
}
function adjustForRightToLeftOrientation(node, dAdjust) {
	console.log("Adjusting for " + node.innerHTML);
	var dWidth = parseFloat(node.getAttribute(ltWidth));
	var xcoord = parseFloat(node.getAttribute(svgXCoord));
	var dAdjustedXCoord = (dAdjust - dWidth) - xcoord;
	node.setAttribute(svgXCoord, dAdjustedXCoord);
//	var isNodeText = node.getAttribute(ltNodeTextItems);
//	if (isNodeText == "false") {
//		setRTLXCoord(node, dAdjust, dWidth);
//	} else {
//		var dNodeTextsWidth = 0.0;
//		var motherId = node.getAttribute(ltMother);
//		var mother = document.getElementById(motherId);
//		if (mother != null) {
//			var mothersDaughtersList = mother.getAttribute(ltDaughters);
//			var mothersDaughters = mothersDaughtersList.split(',');
//			for (var i = 0; i < mothersDaughters.length - 1; i++) {
//				var mothersDaughter = document.getElementById(mothersDaughters[i]);
//				if (mothersDaughter != null && mothersDaughter != node) {
//					var thisOneIsNodeText = mothersDaughter.getAttribute(ltNodeTextItems);
//					if (thisOneIsNodeText == "true") {
//						dNodeTextsWidth += dWidth;
//					}
//				}
//			}
//			console.log("node = " + node.innerHTML);
//			console.log("\tdWidth          = " + dWidth);
//			console.log("\tdAdjust         = " + dAdjust);
//			console.log("\tdNodeTextsWidth = " + dNodeTextsWidth);
//			var xcoord = parseFloat(node.getAttribute(svgXCoord));
//			console.log("\txcoord before   = " + xcoord);
//			setRTLXCoord(node, dAdjust, dWidth + dNodeTextsWidth);
//			xcoord = parseFloat(node.getAttribute(svgXCoord));
//			console.log("\txcoord after    = " + xcoord);
//		}
//	}
	var dXMid = parseFloat(node.getAttribute(ltXMid));
	var dAdjustedXMid = dAdjust - dXMid;
	console.log("\txcoord before = " + xcoord);
	console.log("\txcoord after  = " + dAdjustedXCoord);
	console.log("\txmid before = " + dXMid);
	console.log("\txmid after  = " + dAdjustedXMid);
	node.setAttribute(ltXMid, dAdjust - dXMid);
	adjustCollapsedForRightToLeftOrientration(node, dXMid, dAdjust);
	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	for (var i = 0; i < daughters.length - 1; i++) {
		var daughter = document.getElementById(daughters[i]);
		if (daughter == null) {
			continue;
		}
		adjustForRightToLeftOrientation(daughter, dAdjust);
	}
}
function adjustCollapsedForRightToLeftOrientration(node, dXMid, dAdjust) {
	var nodeId = node.getAttribute(svgId);
	let id = nodeId.substring(4);
	var collapsedNode = document.getElementById('collapsed' + id);
	if (collapsedNode != null) {
		setRTLXCoord(collapsedNode, dAdjust, dEllipsisWidth);
	}
	var lineId = 'line' + id;
	var dLeftmostX = (dAdjust - dXMid) - dEllipsisXOffset;
	var dRightmostX = (dAdjust - dXMid) + dEllipsisXOffset;
	var dTopX = dAdjust - dXMid;
	setRTLCollapsedTriangleLine(dLeftmostX, dTopX, lineId, "1");
	setRTLCollapsedTriangleLine(dTopX, dRightmostX, lineId, "2");
	setRTLCollapsedTriangleLine(dLeftmostX, dRightmostX, lineId, "3");
}
function setRTLXCoord(node, dAdjust, dWidth) {
	var xcoord = parseFloat(node.getAttribute(svgXCoord));
	var dNewXCoord = (dAdjust - dWidth) - xcoord;
	node.setAttribute(svgXCoord, dNewXCoord);
}
function setRTLCollapsedTriangleLine(dX1, dX2, lineId, trianglePart) {
	var line = document.getElementById(lineId + "T" + trianglePart);
	if (line != null) {
		line.setAttribute(svgX1, dX1);
		line.setAttribute(svgX2, dX2);
	}
}

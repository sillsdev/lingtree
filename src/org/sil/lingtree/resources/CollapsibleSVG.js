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
	var rootNode = document.getElementById("node0");
	calculateMaxWidthOfNodes(rootNode);
	calculateXCoordinateAndXMidOfNodes(rootNode, initialXCoord);
	resetAllLines(rootNode);
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
		var dXMid = parseFloat(daughter.getAttribute(ltXMid));
		var motherId = node.getAttribute(svgId);
		adjustLineBetweenNodeAndItsMother(daughters[i], dXMid, motherId);
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
	console.log("calcMaxWidth for " + node.innerHTML);
	console.log("\tdMaxWidthOfDaughters = " + dMaxWidthOfDaughters);
	console.log("\tdWidth               = " + dWidth);
	console.log("\tnumDaughters         = " + numDaughters);
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
	console.log("setDaughtersMaxWidth for " + node.innerHTML);
	console.log("\tnewMaxWidth = " + newMaxWidth);
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
		var dWidth = parseFloat(node.getAttribute(ltWidth));
		var dMaxInlineMothersWidth = parseFloat(node.getAttribute(ltMaxInColumnMothersWidth));
		var dMaxWidthInColumn = Math.max(dWidth, dEllipsisWidth, dMaxInlineMothersWidth);
		node.setAttribute(ltMaxWidthInColumn, dMaxWidthInColumn);
		var xcoord = leftOffset;
		xcoord += (dMaxWidthInColumn - dWidth) / 2;
		node.setAttribute(svgXCoord, xcoord);
		var dXMid = xcoord + (dWidth / 2);
		node.setAttribute(ltXMid, dXMid);
		var nodeId = node.getAttribute(svgId);
		adjustEllipsisTriangleAndTextLocation(nodeId, dXMid);
		var motherId = node.getAttribute(ltMother);
		adjustLineBetweenNodeAndItsMother(nodeId, dXMid, motherId);
		console.log("Collapsed: node = " + node.innerHTML);
		console.log("\twidth         = " + dWidth);
		console.log("\tmomsmaxwidth  = " + dMaxInlineMothersWidth);
		console.log("\tmaxwidthincol = " + dMaxWidthInColumn);
		console.log("\tellipsis      = " + dEllipsisWidth);
		console.log("\toffset        = " + leftOffset);
		console.log("\txcoord        = " + xcoord);
		return;
	}

	var daughtersList = node.getAttribute(ltDaughters);
	var daughters = daughtersList.split(',');
	var numDaughters = daughters.length - 1;
	console.log("cxx: node = " + node.innerHTML);
	console.log("\tdaughtersList = " + daughtersList);
	console.log("\tnum daughters = " + numDaughters);
	if (maxWidthInColumn > maxWidthOfDaughters && numDaughters > 1) {
		daughtersLeftOffset += (maxWidthInColumn - maxWidthOfDaughters) / 2;
	}
	for (let i = 0; i < numDaughters; i++) {
		var gap = horizontalGap;
		var daughterId = daughters[i];
		var daughter = document.getElementById(daughterId);
		if (daughter == null) {
			continue;
		}
		if (i == 0) {
			gap = 0.0;
		}
		calculateXCoordinateAndXMidOfNodes(daughter, daughtersLeftOffset + gap);
		var daughtersMaxWidthInColumn = parseFloat(daughter.getAttribute(ltMaxWidthInColumn));
		daughtersLeftOffset += daughtersMaxWidthInColumn + gap;
	}
	var dXMid = calculateXMidOfNode(maxWidthInColumn, daughters, leftOffset);
//	console.log("dXmid after calc = " + dXMid);
	node.setAttribute(ltXMid, dXMid);
	var width = parseFloat(node.getAttribute(ltWidth));
	var xcoord = dXMid - (width / 2);
	node.setAttribute(svgXCoord, xcoord);
//	console.log("node = "+ node.getAttribute(svgId) + "; " + node.innerHTML);
//	console.log("\tx-coord   = " + xcoord);
//	console.log("\txmid      = " + dXMid);
//	console.log("\twidth     = " + width);
//	console.log("\tmax       = " + maxWidthInColumn);
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
function adjustCollapsedText(nodeId, dX) {
	var text = document.getElementById("collapsed" + nodeId);
	if (text != null) {
		text.setAttribute(svgXCoord, dX);
	}
}
function calculateXMidOfNode(maxWidthInColumn, daughters, leftOffset) {
	var dXMid = leftOffset + (maxWidthInColumn / 2);
	console.log("calc at beginning: dXmid = " + dXMid);
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
	console.log("calc at end      : dXmid = " + dXMid);
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

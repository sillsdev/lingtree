/**
 * Copyright (c) 2025 SIL Global
 * This software is licensed under the LGPL, version 2.1 or later
 * (http://www.gnu.org/licenses/lgpl-2.1.html)
 */
/**
 * Collaspible SVG javascript code
 */

function ProcessCollapsibleNode(nodeId) {
//	alert(nodeId);
	var text = document.getElementById(nodeId);
//	alert(text);
	var widthValue = text.getAttribute('lt:width');
	/*alert(widthValue);*/
	var heightValue = text.getAttribute('lt:height');
	/*alert(heightValue);*/
	var collapsedNodes = text.getAttribute('lt:collapsedNodes');
//	alert('cns=' + collapsedNodes);
	let id = nodeId.substring(4);
	var nodes = collapsedNodes.split(',');
//	alert('len=' + nodes.length);

	var collapsedLines = text.getAttribute('lt:collapsedLines');
//	alert('cls=' + collapsedLines);
	var lines = collapsedLines.split(',');
//	alert('len=' + lines.length);

	var isNodeCollapsed = text.getAttribute('lt:collapsed');
//	alert("collapsed = " + isNodeCollapsed);
	if (isNodeCollapsed == 'true') {
		setVisibilityOfNodesAndLines(nodes, lines,'visible');
//		alert("after nodes and lines to visible");
		setVisibilityOfCollapsedTo(id, 'hidden');
//		alert("after collapsed hidden");
		text.setAttribute('lt:collapsed', 'false');
	} else {
		setVisibilityOfNodesAndLines(nodes, lines, 'hidden');
//		alert("after nodes and lines to hidden");
		setVisibilityOfCollapsedTo(id, 'visible');
//		alert("after collapsed visible");
		text.setAttribute('lt:collapsed', 'true');
	}
}
function setVisibilityOfNodesAndLines(nodes, lines, visibility) {
	for (let i = 0; i < nodes.length; i++) {
//		alert("node " + nodes[i] + " to " + visibility);
		var line = document.getElementById(nodes[i]);
		if (line != null) {
			line.setAttribute('visibility', visibility);
		} else {
			alert("nodes[i] not found: " + nodes[i]);
		}
//		document.getElementById(nodes[i]).setAttribute('visibility', visibility);
	}
	for (let i = 0; i < lines.length; i++) {
//		alert("line " + lines[i] + " to " + visibility);
		var line = document.getElementById(lines[i]);
		if (line != null) {
			line.setAttribute('visibility', visibility);
		} else {
			alert("lines[i] not found: " + lines[i]);
		}
//		document.getElementById(lines[i]).setAttribute('visibility', visibility);
	}
}
function setVisibilityOfCollapsedTo(id, visibility) {
//	alert("collapsed: id = " + id + "; visibility = " + visibility);
	document.getElementById('collapsed' + id).setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T1').setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T2').setAttribute('visibility', visibility);
	document.getElementById('line' + id + 'T3').setAttribute('visibility', visibility);
}

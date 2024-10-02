package org.sil.utility.view;

import java.util.HashSet;
import java.util.Set;

import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

// This comes from
// https://stackoverflow.com/questions/61072150/how-to-overwrite-system-default-keyboard-shortcuts-like-ctrlc-ctrlv-by-using
// accessed on 23 September 2020.

public class FilteringEventDispatcher implements EventDispatcher {
	// This comes from
	// https://stackoverflow.com/questions/61072150/how-to-overwrite-system-default-keyboard-shortcuts-like-ctrlc-ctrlv-by-using
	// accessed on 23 September 2020.

	private final EventDispatcher delegate;
	private final Set<KeyCombination> blacklistedCombos = new HashSet<KeyCombination>();

	// if we ever need to block two or more key events, we'll need this
	// constructor
	public FilteringEventDispatcher(EventDispatcher delegate, KeyCombination... blacklistedCombos) {
		this.delegate = delegate;
		for (KeyCombination kc : blacklistedCombos) {
			this.blacklistedCombos.add(kc);
		}
	}

	public FilteringEventDispatcher(EventDispatcher delegate, KeyCombination accelerator) {
		this.delegate = delegate;
		this.blacklistedCombos.add(accelerator);
	}

	@Override
	public Event dispatchEvent(Event event, EventDispatchChain tail) {
		if (!(event instanceof KeyEvent) || isPermitted((KeyEvent) event)) {
			return delegate.dispatchEvent(event, tail); // forward event to
														// TextArea
		}
		return event; // skip TextArea and enter the bubbling phase
	}

	private boolean isPermitted(KeyEvent event) {
		return blacklistedCombos.stream().noneMatch(combo -> combo.match(event));
	}
}

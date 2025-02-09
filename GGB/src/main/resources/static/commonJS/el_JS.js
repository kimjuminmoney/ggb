let _el = function() {
	const _PATH = '/common/commonJS/el_JS.js';

	function El(element) {
		this._currentElement = element;
	}

	El.prototype = {
		show: function() {
			const showElement = (el) => {
				el.style.display = 'inline-block';
			};

			if (Array.isArray(this._currentElement)) {
				this._currentElement.forEach(showElement);
			} else if (this._currentElement) {
				showElement(this._currentElement);
			}
			return this;
		},
		hide: function() {
			if (Array.isArray(this._currentElement)) {
				this._currentElement.forEach(el => el.style.display = 'none');
			} else if (this._currentElement) {
				this._currentElement.style.display = 'none';
			}
			return this;
		},
		on: function(event, fn) {
			const attachEvent = (element) => {
				element.addEventListener(event, fn);
			};
			if (Array.isArray(this._currentElement)) {
				this._currentElement.forEach(attachEvent);
			} else if (this._currentElement) {
				attachEvent(this._currentElement);
			}
			return this;
		},
		val: function(value) {
			if (value === undefined) {
				if (Array.isArray(this._currentElement)) {
					return this._currentElement.map(el => el.value);
				} else if (this._currentElement) {
					return this._currentElement.value;
				}
			} else {
				const setValue = (element) => {
					if (element.tagName === 'SELECT' && element.multiple) {
						Array.from(element.options).forEach(option => {
							option.selected = value.includes(option.value);
						});
					} else {
						element.value = value;
					}
				};

				if (Array.isArray(this._currentElement)) {
					this._currentElement.forEach(setValue);
				} else if (this._currentElement) {
					setValue(this._currentElement);
				}
				return this;
			}
		},
		innerText : function(text) {
			this._currentElement.innerText = text
		},
		innerHTML : function(html) {
			this._currentElement.innerHTML = html
		},
		disable : function (){
			this._currentElement.disabled = true;
			this._currentElement.classList.add('disabled');
		},
		enable : function (){
			this._currentElement.disabled = false;
			this._currentElement.classList.remove('disabled');
		},
		isDisable : function (){
			return this._currentElement.disabled;
		},
		/**
		 * Form 태그 submit
		 */
		submit : function (){
			this._currentElement.submit();
		},
		css: function(prop, value) {
			if (typeof prop === 'object') {
				for (let key in prop) {
					this.css(key, prop[key]);
				}
			} else {
				const applyStyle = (element) => {
					element.style[prop] = value;
				};

				if (Array.isArray(this._currentElement)) {
					this._currentElement.forEach(applyStyle);
				} else if (this._currentElement) {
					applyStyle(this._currentElement);
				}
			}
			return this;
		}
	};

	return {
		$: function(el) {
			let elements;
			if (el.startsWith('#')) {
				elements = document.getElementById(el.slice(1));
			} else if (el.startsWith('.')) {
				elements = document.getElementsByClassName(el.slice(1));
			} else if (el.startsWith('[')) {
				elements = document.querySelectorAll(el);
			} else {
				elements = document.getElementsByTagName(el);
			}

			if (elements instanceof NodeList || elements instanceof HTMLCollection) {
				elements = Array.from(elements);
			}
			elements = elements.length === 1 ? elements[0] : elements || null;
			return new El(elements);
		},
		ready: function(callback) {
			if (document.readyState !== 'loading') {
				callback();
			} else {
				document.addEventListener('DOMContentLoaded', callback);
			}
		},
		load: function(callback) {
			if (document.readyState === 'complete') {
				callback();
			} else {
				window.addEventListener('load', callback);
			}
		}
	};
};

const $el = _el();

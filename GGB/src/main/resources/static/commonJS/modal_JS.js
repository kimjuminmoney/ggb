/**
 * 
 */
let _modal = function() {
	const _PATH = '/common/commonJS/modal_JS.js'
	let _Self;

	return _Self = {

			init: function() {
				MicroModal.init();
			},
			show: function(modal) {
				if (modal) {
					MicroModal.show(modal);
				} else {
					MicroModal.show('modal');
				}
			},
			close: function(modal) {
				if (modal) {
					MicroModal.close(modal);
				} else {
					MicroModal.close('modal');
				}
			},
			setModal: function(obj) {
				let title = obj['title'];
				let content = obj['content'];
				console.log(obj)
				$el.$('#modal-title').innerText(title);
				$el.$('#modal-content').innerHTML(content);
				let btnArr = obj['btn'];
				let el;
				for (let btn of btnArr) {
					el = '#modal-btn-' + btn
					$el.$(el).show();
					$el.$(el).on('click', obj[btn] ? obj[btn] : function(e){$modal.close()})
					console.log(el)
					console.log(obj[btn])
					console.log(btn)
				}

			}
	}
}
const $modal = _modal();
$modal.init();

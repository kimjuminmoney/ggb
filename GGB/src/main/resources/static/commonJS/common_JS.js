/**
 * 
 */
let _commons = function() {
	const _PATH = '/common/commonJS/common_JS.js'
	let _Self;

	return _Self = {

		axios: {
			get: function(url, param, fn) {
				let success = fn['success'] || function() { }
				let error = fn['error'] || function(err) { console.error(err) }
				let final = fn['final'] || function() { }

				axios.get(url, {
					params: param
				}).then(success)
					.catch(error)
					.finally(final);
			}

		},
		cal: {
			formatFileSize: function(bytes, decimals = 2) {
				if (bytes === 0) return '0 Bytes';
				const k = 1024;
				const dm = decimals < 0 ? 0 : decimals;
				const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

				const i = Math.floor(Math.log(bytes) / Math.log(k));
				return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
			}
		},
		grid: {
			//grid 컬럼 생성 함수
			getCol: function(arr) {
				let colArr = []
				for (let row of arr) {
					let colObj = {
						'header': row[0],
						'name': row[1]
					};
					colArr.push(colObj);
				}
				return colArr;
			}
		},
		validate: function(elId) {
			const el = $el.$(elId);
			if (!el) {
				console.error(`[${elId}] DOM 객체가 없습니다.`);
				return elId; // 객체가 없으면 해당 ID 반환
			}

			function validateElement(element) {
				// input 태그인 경우
				if (element.tagName === 'INPUT' || element.tagName === 'SELECT' || element.tagName === 'TEXTAREA') {
					const value = element.value.trim();
					if (value === '') {
						console.error(`[${element.id || elId}]에 값이 없습니다.`);
						return element.id || elId;
					}

					// 추가적인 유효성 검사 (예: 이메일, 숫자 등)
					if (element.type === 'email' && !/\S+@\S+\.\S+/.test(value)) {
						console.error(`[${element.id || elId}] 유효하지 않은 이메일 형식입니다.`);
						return element.id || elId;
					}
					// 필요에 따라 다른 타입의 유효성 검사 추가
				}

				// 자식 요소 검사
				if (element.children && element.children.length > 0) {
					for (let child of element.children) {
						const result = validateElement(child);
						if (result !== true) {
							return result; // 유효하지 않은 자식 요소 발견 시 즉시 반환
						}
					}
				}

				return true; // 모든 검사 통과
			}

			return validateElement(el._currentElement);
		}
	}
}
const $cu = _commons();
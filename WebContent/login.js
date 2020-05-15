/* ログインファンクション */
function login() {
	// 入力されたユーザーIDとパスワード
	var userId = $('#js-login-id').val()
	var password = $('#js-login-pass').val()
	var requestQuery = {
		id : userId,
		pass : password
	};
	// サーバーからデータを取得する
	$.ajax({
		type : 'GET',
		dataType:'json',
		url : '/Syain/api/LoginServlet',
		data : requestQuery,
		success : function(json) {
			console.log(json);
			location.href = './shainindex.html'
				alert('ログインに成功しました');
		//	if(json === 'ok'){
		//		location.href = './shainindex.html'
		//	}else{
		//		var message = '<p>' + 'ログインできませんでした'

		//	}

		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			console.log(errorThrown)
		}
	});
}


/**
 * 読み込み時の動作
 */
$(document).ready(function() {

	// ログインボタンを押したときのイベント
	$('#js-login-button').click(login);


});
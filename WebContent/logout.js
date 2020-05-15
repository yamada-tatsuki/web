/* ログインファンクション */
function logout() {
	// サーバーからデータを取得する
	$.ajax({
		type : 'POST',
		url : '/Syain/api/LogoutServlet',
		dataType : 'json',
		data : requestQuery,
		success : function (json) {
			console.log('返却地',json);
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			// サーバーとの通信に失敗した時の処理
			alert('削除することができませんでした');
			console.log(errorThrown);
		}
	})
	}


/**
 * 読み込み時の動作
 */
$(document).ready(function() {

	// ログインボタンを押したときのイベント
	$('#js-logout-button').click(logout);


});
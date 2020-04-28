// AjaxでJSONを取得する
function executeAjax () {
	'use strict';

	// ?以降のパラメータを取得
	// 今回で言うとhttp://localhost:8080/wt1/hobby.html?q=0001でいう0001が取得される
	var parameter  = location.search.substring( 1, location.search.length );
	parameter = decodeURIComponent( parameter );
	parameter = parameter.split('=')[1];

	// --------------- TODO 編集ここから---------------
	var requestQuery = { syainid  : parameter};
	//console.log(requestQuery);

	$.ajax({
		type : 'GET',
		url : '/Syain/api/bushohenshu',
		dataType : 'json',
		data :requestQuery,
		success : function (json) {

					// サーバーと䛾通信に成功した時䛾処理
					// 確認䛾ために返却値を出力
					console.log(json);
					// 取得したデータを画面に表示する
					// HTML䛾内容を文字列結合で生成する。
					var tableElemnt = '<table>';
					for (var i=0; i < json.length; i++) {
					tableElemnt += '<tr> '
					+'<td>' + hobby.hobbyCategory + '</td>'
					+'<td>' + hobby.hobby +'</td>'
					+ '</tr>'
					+'</table>';

					}
					// HTMLに挿入
					$('#hobby').html(tableElemnt);

					},error : function(XMLHttpRequest, textStatus, errorThrown) { //successとセット
					// サーバーと䛾通信に失敗した時䛾処理
					//alert('データ䛾通信に失敗しました');
					console.log(errorThrown)
					}
					});

	// ---------------ここまで---------------

}

$(document).ready(function () {
	'use strict';

	// 初期表示用
	executeAjax();

	// 更新ボタンにイベント設定
	$('#searchBtn').bind('click',executeAjax);

});
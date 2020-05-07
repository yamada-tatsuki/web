// AjaxでJSONを取得する
/*function executeAjax () {
	'use strict';

	// ?以降のパラメータを取得
	// 今回で言うとhttp://localhost:8080/wt1/hobby.html?q=0001でいう0001が取得される
	var parameter  = location.search.substring( 1, location.search.length );
	parameter = decodeURIComponent( parameter );
	parameter = parameter.split('=')[1];

	// --------------- TODO 編集ここから---------------
	var requestQuery = { syainid  : parameter};*/

var edit = function(){

	var parameter = location.search.substring(1, location.search.length);
	parameter = decodeURIComponent(parameter);
	parameter = parameter.split('=')[1];


	var inputbushoname = $('#js-settei-bushoname').val();

	var requestQuery = {
			NewbushoName : inputbushoname,
			q : parameter
	} ;
	console.log(requestQuery);

$.ajax({
	type : 'POST',
	url : '/Syain/api/editbushokanri',
	dataType : 'json',
	data : requestQuery,
	success : function (json) {
		console.log('返却地',json);


	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		// サーバーとの通信に失敗した時の処理
		alert('設定することができませんでした。');
		console.log(errorThrown);
	}
})
}

$(document).ready(function () {
	//'use strict';
	$('#js-bushosettei-button').click(edit);
	// 初期表示用
	//executeAjax();
	// 更新ボタンにイベント設定
//	$('#searchBtn').bind('click',executeAjax);

});
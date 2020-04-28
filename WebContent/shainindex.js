// AjaxでJSONを取得する
function executeAjax () {
	'use strict';

	var parameter  = location.search.substring( 1, location.search.length );
	parameter = decodeURIComponent( parameter );
	parameter = parameter.split('=')[1];

	var requestQuery = { s : parameter} ;
	console.dir(requestQuery);
	$.ajax({
		type : 'GET',
		url : '/Syain/api/shainindex',
		dataType : 'json',
		data :requestQuery,
		success : function (json) {
			console.log(json);


			for (var i = 0; i < json.length; i++) {

				var element = json[i];

				var record = '<tr>'
					+ '<td>' + element.shainId + '</td>'
					+ '<td>' + element.shainName + '</td>'
					+ '<td>' + '<button class=edit name= "edit" >編集</button>' + '</td>'
					+ '<td>' + '<button class=deletebusho type="submit" >削除</button>' + '</td>'
					+ '</tr>';

				$('#table_data').append(record)
			}
			//$('.delete').click('road',deletebusho);
		//	console.log(json[i]);
		}
	});
}


/*var deletebusho = function(){
	var inputbushoId = document.activeElement.value;
	var restQqueuery = { delete1 : inputbushoId} ;
	console.log(requestQuery);
}
$.ajax({
	type : 'POST',
	url : '/Syain/deletebushokanri',
	dataType : 'json',
	//data :requestQuery,
	success : function (json) {
		console.log(json);
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		// サーバーとの通信に失敗した時の処理
		alert('削除することができませんでした');
		console.log(errorThrown);
	}
});*/


$(document).ready(function () {
	'use strict';

	// 初期表示用
	executeAjax();

	$('#table_data').ready('road',executeAjax);

});
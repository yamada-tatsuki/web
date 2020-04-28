// AjaxでJSONを取得する
function executeAjax () {
	'use strict';

	var parameter  = location.search.substring( 1, location.search.length );
	parameter = decodeURIComponent( parameter );
	parameter = parameter.split('=')[1];

	var requestQuery = { q : parameter} ;
	console.dir(requestQuery);
	$.ajax({
		type : 'GET',
		url : '/Syain/api/bushokanri',
		dataType : 'json',
		data :requestQuery,
		success : function (json) {

			//var syainName = json[0].syainName;
			//document.getElementById("title_name").innerHTML = syainName + "さんの趣味一覧です！";

			for (var i = 0; i < json.length; i++) {

				var element = json[i];

				var record = '<tr>'
					+ '<td>' + element.bushoId + '</td>'
					+ '<td>' + element.bushoName + '</td>'
					+ '<td>' + '<button class=edit name= "edit" >編集</button>' + '</td>'
					+ '<td>' + '<button class=delete type="submit" name="delete" value="'+ element.bushoId +'">削除</button>' + '</td>'
					+ '</tr>';

				$('#table_data').append(record)
			}
			$('.delete').click(deletebusho);
		}
	});
}


var deletebusho = function(){
	var inputbushoId = document.activeElement.value;
	var requestQuery = { d : inputbushoId} ;
	console.log(requestQuery);

$.ajax({
	type : 'POST',
	url : '/Syain/api/deletebushokanri',
	dataType : 'json',
	success : function (json) {
		console.log('返却地',json);
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		// サーバーとの通信に失敗した時の処理
		alert('サーバーとの通信ができませんでした');
		console.log(errorThrown);
	}
})
}

$(document).ready(function () {
	'use strict';

	// 初期表示用
	executeAjax();

	$('#table_data').ready('road',executeAjax);

});
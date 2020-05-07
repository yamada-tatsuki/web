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
					+ '<td>' + '<button class=edit name= "edit" value="'+ element.shainId +'">編集</button>' + '</td>'
					+ '<td>' + '<button class=delete type="submit" name="delete" value="'+ element.shainId +'" button onclick="func1()" >削除</button>' + '</td>'
					+ '</tr>';

				$('#table_data').append(record)
			}
			$('.delete').click(deleteshain);

			$('.edit').click(editshain);
		}
	});
}

var editshain = function(){

	var shainId = document.activeElement.value;
	location.href='./shainedit.html?shainId=' +shainId;
	console.log(shainId);

}

function func1() {
    document.location.reload();
  }

var deleteshain = function(){
	var inputshainId = document.activeElement.value;
	var  requestQuery = { shaindelete : inputshainId} ;
	console.log(requestQuery);

$.ajax({
	type : 'POST',
	url : '/Syain/api/deleteshainkanri',
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


$(document).ready(function () {
	'use strict';

	// 初期表示用
	executeAjax();

	$('#table_data').ready('road',executeAjax);
});
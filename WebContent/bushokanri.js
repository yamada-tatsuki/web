// AjaxでJSONを取得する
function executeAjax () {
	'use strict';
	var requestQuery = { q  : $('#q').val()};
	console.dir(requestQuery);
	$.ajax({
		type : 'GET',
		url : '/Syain/api/bushokanri',
		dataType : 'json',
		data :requestQuery,
		success : function (json) {

			var syainName = json[0].syainName;
			document.getElementById("title_name").innerHTML = syainName + "さんの趣味一覧です！";

			for (var i = 0; i < json.length; i++) {

				var element = json[i];

				var record = '<tr>'
					+ '<td>' + element.no + '</td>'
					+ '<td>' + element.hobbyCategory + '</td>'
					+ '<td>' + element.hobby + '</td>'
					+ '</tr>';

				$('#table_data').append(record)
			}

					}
	});
}

$(document).ready(function () {
	'use strict';

	// 初期表示用
	executeAjax();

	// 更新ボタンにイベント設定
	$('#searchBtn').bind('click',executeAjax);

});
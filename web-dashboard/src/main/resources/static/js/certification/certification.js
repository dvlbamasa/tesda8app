$.getJSON("https://spreadsheets.google.com/feeds/list/1t2_HHLkibAybPORmXuDDEFEyetC3p7r1blQRpjzinXg/od6/public/values?alt=json", function (data) {

    var sheetData = data.feed.entry;
    var i;
    console.log(data.feed.entry);
    for (i = 0; i < sheetData.length; i++) {
        var name = data.feed.entry[i]['gsx$_cn6ca']['$t'];
        var age = data.feed.entry[i]['gsx$_cokwr']['$t'];
        var email = data.feed.entry[i]['gsx$_cpzh4']['$t'];

        document.getElementById('demo').innerHTML += ('<tr>'+'<td>'+name+'</td>'+'<td>'+age+'</td>'+'<td>'+email+'</td>'+'</tr>');

    }
});

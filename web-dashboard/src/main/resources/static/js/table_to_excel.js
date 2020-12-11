function exportTableToExcel(tableID, filename = ''){
    $('table').stickyTableHeaders('destroy');

    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    tableHTML = tableHTML.replace(/#/g, '%23');

    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';

    // Create download link element
    downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
    }

    $('table').stickyTableHeaders({fixedOffset: $('.page-header')});
}

function exportScholarshipTableToExcel(tableID, filename = ''){
    $('table').stickyTableHeaders('destroy');

    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    tableHTML = tableHTML.replace(/#/g, '%23');

    const month = document.getElementById('monthValue').value;
    const year = document.getElementById('yearValue').value;
    const reportDate = " (" + month + ", " + year + ")";
    // Specify file name
    filename = filename?filename + reportDate +'.xls':'excel_data.xls';

    // Create download link element
    downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
    }

    $('table').stickyTableHeaders({fixedOffset: $('.page-header')});
}
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
    swal("Downloading Report.", "Your report is being downloaded in a moment.", "success");
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
}

/*
    @cellList: cell values on the fetched objects that refers to the auto formatting of percentage
 */
var dashboardTablesToExcel = (function() {

    return function() {
        const tables = [
            'po-table',
            'cert-table',
            'ro-gs-table',
            'ro-t2-table',
            'institution-table',
            'enterprise-gs-table',
            'enterprise-t2-table',
            'community-table',
            'tti-table'];
        const wsnames = [
            'PO',
            'Certification Rate',
            'RO Per Mode (GS)',
            'RO Per Mode (T2)',
            'Institution-based',
            'Enterprise-based (GS)',
            'Enterprise-based (T2)',
            'Community-based',
            'TTI'];
        const wbname = 'Accomplishment Reports.xls';
        var workbook = XLSX.utils.book_new();

        for (var i = 0; i < tables.length; i++) {
            var ws1 = XLSX.utils.table_to_sheet(document.getElementById(tables[i]));
            // certification rate
            if (i === 1) {
                ws1['D2']['v'] = formatPercentage(ws1['D2']['v']);
                ws1['D2']['t'] = 's';
            }
            for (var key = 3; key < 18; key++) {
                const cellList = ['D', 'G', 'J', 'M'];
                updatePercentageValues(ws1, cellList, key);
            }
            XLSX.utils.book_append_sheet(workbook, ws1, wsnames[i]);
        }
        XLSX.writeFile(workbook, wbname);
    }
})();


var planningTablesToExcel = (function() {
    return function() {
        var tables = [
            'tesdpp-table',
            'tesdrp-table',
            'tesdp-table',
            'sto-table',
            'gass-table'];
        var wsnames = [
            'TESD Policy Program',
            'TESD Regulatory Program',
            'TESD Programs',
            'Support to Operations',
            'GA Support Services'];
        var wbname = 'OPCR.xls';
        $('table').stickyTableHeaders('destroy');

        var workbook = XLSX.utils.book_new();

        for (var i = 0; i < tables.length; i++) {
            var ws1 = XLSX.utils.table_to_sheet(document.getElementById(tables[i]));

            for (var key = 3; key < 80; key++) {
                const cellList = ['F', 'I', 'L', 'O', 'R', 'U', 'X', 'AA'];
                updatePercentageValues(ws1, cellList, key);
            }
            XLSX.utils.book_append_sheet(workbook, ws1, wsnames[i]);
        }
        XLSX.writeFile(workbook, wbname);
        $('table').stickyTableHeaders({fixedOffset: $('.page-header')});
    }
})()


var scholarshipTablesToExcel = (function() {
    return function() {
        var tables = [
            'twsp-table',
            'pesfa-table',
            'step-table',
            'resp-table',
            'uaqtea-sb-table',
            'uaqtea-diploma-table'
        ];
        var wsnames = [
            'TWSP',
            'PESFA',
            'STEP',
            'RESP',
            'UAQTEA-SB',
            'UAQTEA-DIPLOMA'
        ];
        const month = document.getElementById('monthValue').value;
        const year = document.getElementById('yearValue').value;
        const reportDate = " (" + month + ", " + year + ")";
        var wbname = 'Scholarship Monthly Accomplishment ' + reportDate + '.xls';
        swal("Downloading Report.", "Your report is being downloaded in a moment.", "success");

        var workbook = XLSX.utils.book_new();

        for (var i = 0; i < tables.length; i++) {
            var ws1 = XLSX.utils.table_to_sheet(document.getElementById(tables[i]));
            for (var key = 4; key < 11; key++) {
                const cellList = ['H', 'J', 'L', 'N', 'P', 'R'];
                updatePercentageValues(ws1, cellList, key);
            }
            XLSX.utils.book_append_sheet(workbook, ws1, wsnames[i]);
        }
        XLSX.writeFile(workbook, wbname);
    }
})()

function updatePercentageValues(workSheet, cellList, key) {
    for (var index = 0; index < cellList.length; index++) {
        if (workSheet[cellList[index]+key] != null){
            workSheet[cellList[index]+key]['v'] = formatPercentage(workSheet[cellList[index]+key]['v']);
            workSheet[cellList[index]+key]['t'] = 's';
        }
    }
}

function formatPercentage(num) {
    var percent;
    percent = (100*num).toFixed(1);
    return percent + '%';
}
window.onload = function () {
    setTimeout(function () {
        document.getElementById('downloadGraphButton').disabled=false;
    }, 3000);

    var papGroupType = $('#papGroupType').val();
    var papName = $('#papName').val();
    var measure = $('#measure').val();
    var year = $('#year').val();
    var month = $('#month').val();
    var pageType = $('#pageType').val();


    $.getJSON('/api/papData/' + papGroupType + '/papGroup/filter',
        {
            measure: measure,
            papName: papName,
            year: year,
            month: month,
            pageType : pageType
        },
        function(data) {
            var data = data;
            for (var i = 0; i < data.length; i++) {
                var operatingUnitDataList = data[i].operatingUnitDataList;
                console.log(operatingUnitDataList);
                var successIndicator = data[i];
                var dataPointsTarget = [];
                var dataPointsOutput = [];
                var dataPointsRate = [];
                for (var j = 0; j < operatingUnitDataList.length; j++) {
                    dataPointsTarget.push({
                        label: checkLabel(operatingUnitDataList[j].operatingUnitType),
                        y: operatingUnitDataList[j].target
                    });

                    dataPointsOutput.push({
                        label: checkLabel(operatingUnitDataList[j].operatingUnitType),
                        y: operatingUnitDataList[j].output
                    });

                    dataPointsRate.push({
                        label: checkLabel(operatingUnitDataList[j].operatingUnitType),
                        y: operatingUnitDataList[j].rate
                    });

                }
                generateGraph(dataPointsTarget, dataPointsOutput, dataPointsRate,
                    "successIndicatorGraph" + successIndicator.id, successIndicator.papName + " - " + successIndicator.target + (successIndicator.isPercentage ?  '%' : "") + ' ' + successIndicator.measures, "Target", "Total");

            }
        }
    );
}


function generateGraph(dataPointsTarget, dataPointsOutput, dataPointsRate,  chartName, title, legend1, legend2) {
    var chart = new CanvasJS.Chart(chartName, {
        animationEnabled: true,
        title:{
            text:title
        },
        axisY: {
            lineColor: "#369EAD",
            titleFontColor: "#369EAD",
            labelFontColor: "#369EAD"
        },
        axisY2:{
            title: "Percent (%)",
            lineColor: "#7F6084",
            titleFontColor: "#7F6084",
            labelFontColor: "#7F6084"
        },
        data: [{
            type: "column",
            name: legend1,
            indexLabel: "{y}",
            indexLabelFontSize: 12,
            showInLegend: true,
            dataPoints : dataPointsTarget,
        },
            {
                type: "column",
                name: legend2,
                indexLabel: "{y}",
                indexLabelFontSize: 12,
                showInLegend: true,
                dataPoints : dataPointsOutput,
            },
            {
                type: "line",
                name: "Rate",
                indexLabelFormatter: function(e){
                    return e.dataPoint.y + " " + "%" ;
                },
                axisYType: "secondary",
                indexLabel: "{y}",
                indexLabelFontSize: 12,
                showInLegend: true,
                dataPoints : dataPointsRate,
            }
        ]
    });
    chart.render();
}

function checkLabel(label) {
    switch (label) {
        case "LEYTE_PO":
            return "Leyte PO";
        case "BILIRAN_PO":
            return "Biliran PO";
        case "SAMAR_PO":
            return "Samar PO";
        case "EASTERN_SAMAR_PO":
            return "Eastern Samar PO";
        case "NORTHERN_SAMAR_PO":
            return "Northern Samar PO";
        case "SOUTHERN_LEYTE_PO":
            return "Southern Leyte PO";
        case "TESDA_RO":
            return "TESDA RO";
        case "TOTAL":
            return "Total";
        case "RTC_TTI":
            return "RTC";
        case "CNVS_TTI":
            return "CNVS";
        case "PTC_LEYTE_TTI":
            return "PTC Leyte";
        case "CNSAT_TTI":
            return "CNSAT";
        case "PTC_BILIRAN_TTI":
            return "PTC Biliran";
        case "PTC_SO_LEYTE_TTI":
            return "PTC S. Leyte";
        case "PTC_SAMAR_TTI":
            return "PTC Samar";
        case "PTC_E_SAMAR_TTI":
            return "PTC E. Samar";
        case "SNSAT_TTI":
            return "SNSAT";
        case "BNAS_TTI":
            return "BNAS";
        case "ANAS_TTI":
            return "ANAS";
        case "PTC_N_SAMAR_TTI":
            return "PTC No. Samar";
        case "BCAT_TTI":
            return "BCAT";
        case "LNAIS_TTI":
            return "LNAIS";
    }

}


$("#downloadGraphButton").click(function(){
    swal("Exporting Data as PDF.", "Your report is being downloaded in a moment.", "success");
    window.scrollTo(0, 0);
    var HTML_Width = $(".canvas_div_pdf").width();
    var HTML_Height = $(".canvas_div_pdf").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;

    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;

    var papGroupTypeLabel = $('#papGroupTypeLabel').val();
    html2canvas($(".canvas_div_pdf")[0],{allowTaint:true}).then(function(canvas) {
        canvas.getContext('2d');

        console.log(canvas.height+"  "+canvas.width);


        var imgData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
        pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);


        for (var i = 1; i <= totalPDFPages; i++) {
            var offset;
            if (i === 3) {
                offset = 60;
            } else if (i > 3) {
                offset += 30;
            } else {
                offset = 0;
            }
            pdf.addPage(PDF_Width, PDF_Height);
            pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4) + offset,canvas_image_width,canvas_image_height);
        }

        pdf.save("TESDA-" + papGroupTypeLabel + " Graphs.pdf");
    });
});

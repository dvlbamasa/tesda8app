
$("#downloadButton").click(function(){
    swal("Exporting Data as PDF.", "Your report is being downloaded in a moment.", "success");
    window.scrollTo(0, 0);
    var HTML_Width = $(".certificateLayout").width();
    var HTML_Height = $(".certificateLayout").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width)+(top_left_margin*2);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;
    console.log("HTML WIDTH: " + HTML_Width);
    console.log("HTML HEIGHT: " + HTML_Height);

    console.log("PDF_Width: " + PDF_Width);
    console.log("PDF_Height: " + PDF_Height);
    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;


    html2canvas($(".certificateLayout")[0],{allowTaint:true}).then(function(canvas) {
        canvas.getContext('2d');

        console.log(canvas.height+"  "+canvas.width);

        var imgData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new jsPDF('p', 'pt',  [PDF_Width, 1500]);
        pdf.addImage(imgData, 'JPG', top_left_margin, 250,canvas_image_width,canvas_image_height);


        for (var i = 1; i <= totalPDFPages; i++) {
            pdf.addPage(PDF_Width, PDF_Height);
            pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4) + offset,canvas_image_width,canvas_image_height);
        }

        var fullName = $('#fullName').val();
        var qualification = $('#qualification').val();

        pdf.save("NTTC - " + fullName + " (" + qualification +  ").pdf");
    });
});
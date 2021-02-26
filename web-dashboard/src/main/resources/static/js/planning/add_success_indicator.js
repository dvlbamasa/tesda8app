window.onload = function () {
    document.getElementById("successIndicatorType").onchange = function() {
        var selectValue = document.getElementById("successIndicatorType");
        if (selectValue.value === "RO_PO_TTI") {
            document.getElementById('poTable').style.display="";
            document.getElementById('ttiTable').style.display="";
        } else if (selectValue.value === "RO_PO") {
            document.getElementById('poTable').style.display="";
            document.getElementById('ttiTable').style.display="none";

        } else if (selectValue.value === "TTI") {
            document.getElementById('poTable').style.display="none";
            document.getElementById('ttiTable').style.display="";
        }
    }
}


$("#submitButton").click(function(event) {
    if($('#addSuccessIndicatorForm').valid()) {
        event.preventDefault();
        swal({
            title: "Add Success Indicator?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicator successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addSuccessIndicatorForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

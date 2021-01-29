window.onload = function () {
        var selectValue = document.getElementById("certificateType");
        if (selectValue.value === "NTTC") {
            document.getElementById('clnNtcNumber').disabled=false;
            document.getElementById('qualificationTitle').disabled=true;
            document.getElementById('sector').disabled=true;

            $('#sector').prop("disabled", true);
            $('.selectpicker').selectpicker('refresh');
        } else if (selectValue.value === "TM") {
            document.getElementById('clnNtcNumber').disabled=true;
            document.getElementById('qualificationTitle').disabled=true;
            document.getElementById('sector').disabled=true;

            $('#sector').prop("disabled", true);
            $('.selectpicker').selectpicker('refresh');
        } else if (selectValue.value === "NC") {
            document.getElementById('clnNtcNumber').disabled=true;
            document.getElementById('qualificationTitle').disabled=false;
            document.getElementById('sector').disabled=false;

            $('#sector').prop("disabled", false);
            $('.selectpicker').selectpicker('refresh');
        }

};

$("#submitButton").click(function(event) {
    if ( $('#updateCertificateForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Certificate?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Certificate successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateCertificateForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

window.onload = function () {
    document.getElementById('validityDiv').style.display="none";
    document.getElementById('dateOfApprovalDiv').style.display="none";

    document.getElementById("natureOfAppointmentDetails.natureOfAppointmentType").onchange = function() {
        var natureOfAppointmentTypeValue = document.getElementById("natureOfAppointmentDetails.natureOfAppointmentType");
        if (natureOfAppointmentTypeValue.value === "REGULAR") {
            document.getElementById('validityDiv').style.display="none";
        } else {
            document.getElementById('validityDiv').style.display="";
        }
    }
    document.getElementById("remarkDetails.remarkType").onchange = function() {
        var remarkDetailsRemarkTypeValue = document.getElementById("remarkDetails.remarkType");
        if (remarkDetailsRemarkTypeValue.value === "ADDITIONAL") {
            document.getElementById('dateOfApprovalDiv').style.display="none";
        } else {
            document.getElementById('dateOfApprovalDiv').style.display="";
        }
    }

}

    $("#submitButton").click(function(event) {
    if ( $('#addTrainer').valid()) {
        event.preventDefault();
        swal({
            title: "Add Trainer?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Trainer successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addTrainer').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

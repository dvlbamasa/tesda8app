window.onload = function () {
    document.getElementById('submitButton1').disabled=false;
}

$("#submitButton1").click(function(event) {
    if($('#tesdppForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Success Indicators?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicators successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#tesdppForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

$("#submitButton2").click(function(event) {
    if($('#tesdrpForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Success Indicator?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicators successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#tesdrpForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

$("#submitButton3").click(function(event) {
    if($('#tesdpForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Success Indicators?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicators successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#tesdpForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#submitButton4").click(function(event) {
    if($('#stoForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Success Indicators?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicators successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#stoForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#submitButton5").click(function(event) {
    if ($('#gassForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Success Indicators?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicators successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#gassForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
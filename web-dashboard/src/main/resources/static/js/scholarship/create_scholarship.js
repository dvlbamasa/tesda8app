window.onload = function (){
    document.getElementById('submitButton1').disabled=false;
    $("#submitButton1").click(function(event) {
        if($('#updateScholarshipForm').valid()) {
            event.preventDefault();
            var currentElement = $(this);
            swal({
                title: "Update Scholarship Accomplishment?",
                text: "Kindly check the details before saving.",
                icon: "warning",
                buttons: ["No", "Yes"],
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        swal("Scholarship Accomplishment successfully updated!", {
                            icon: "success",
                        }).then((value) => {
                            $('#updateScholarshipForm').submit();
                        });
                    } else {
                        swal("Verify the details before saving.");
                    }
                });
        }
    });
}


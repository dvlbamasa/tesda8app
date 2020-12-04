$(document).ready(function(){
    var expiredDocumentCount = $('#expiredDocumentSize').val();
    console.log(expiredDocumentCount + ' COUNT');
    if (expiredDocumentCount > 0) {
        $("#myToast").toast({
            autohide: false
        });
        $("#myToast").toast('show');
    }
});
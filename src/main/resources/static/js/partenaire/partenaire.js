$(document).ready(function () {

    $("#add-partenaire-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        addPartner();

    });

});

function addPartner() {
    let partner = {
        nom: $("#nom").val(),
        mail: $("#email").val(),
        ninea: $("#ninea").val(),
        tel: $("#tel").val(),
        adresse: $("#adresse").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/transfert/partenaire",
        data: JSON.stringify(partner),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            setTextToAttrib(null, true);
            toastSuccess('Opération effectuée avec succès', 'Success');
            listPartner();
        },
        error: function (e) {

            let json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            // $("#btn-search").prop("disabled", false);

        }
    });
}

function listPartner() {
    $.ajax({
        type: "GET",
        url: "/transfert/partenaire/list",
        success: function (data) {

            let json =
                "<table class=\"table table-hover\">" +
                "                            <thead>" +
                "                            <tr>\n" +
                "                                <th>Ninea</th>" +
                "                                <th>Nom</th>" +
                "                                <th>Téléphone</th>" +
                "                                <th>Email</th>" +
                "                                <th>Adresse</th>" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "<tbody>";
            for (const p of data.data) {
                console.log(p);
                json = json +
                    "<tr>" +
                    "<td>" + p.ninea + "</td>" +
                    "<td>" + p.nom + "</td>" +
                    "<td>" + p.tel + "</td>" +
                    "<td>" + p.mail + "</td>" +
                    "<td>" + p.adresse + "</td>" +
                    "<td>" +
                    "<button class='btn btn-primary' data-target='#partenaireModal' data-toggle='modal'" +
                    " onclick='loadPartner(" + p + ")' id='update-partner'>Modifier</button>" +
                    "</td>" +
                    "</tr>"
            }

            json = json + "</tbody>" +
                "                        </table>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            // $("#btn-search").prop("disabled", false);

        },
        error: function (e) {
            console.log(e);
        }
    });

}

function loadPartner(partner) {
    console.log(partner);
    if (partner) {
        setTextToAttrib(partner, false)
    } else console.log('partner is not define')
}

function setTextToAttrib(val, clear) {
    if (clear) {
        $("#nom").val('');
        $("#email").val('');
        $("#ninea").val('');
        $("#tel").val('');
        $("#adresse").val('');
    } else {
        $("#nom").val(val.nom);
        $("#email").val(val.mail);
        $("#ninea").val(val.ninea);
        $("#tel").val(val.tel);
        $("#adresse").val(val.adresse);
    }
}

function toastSuccess(message, title) {
    $.toast({
        text: message,
        title: title,
        showHideTransition: 'slide',  // It can be plain, fade or slide
        bgColor: 'green',              // Background color for toast
        textColor: '#fff',            // text color
        allowToastClose: false,       // Show the close button or not
        hideAfter: 5000,              // `false` to make it sticky or time in milliseconds to hide after
        stack: 5,                     // `false` to show one stack at a time count showing the number of toasts that can be shown at once
        textAlign: 'left',            // Alignment of text i.e. left, right, center
        position: 'top-right'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
    })
}

function toastError(message, title) {
    $.toast({
        text: message,
        title: title,
        showHideTransition: 'slide',  // It can be plain, fade or slide
        bgColor: 'red',              // Background color for toast
        textColor: '#fff',            // text color
        allowToastClose: false,       // Show the close button or not
        hideAfter: 5000,              // `false` to make it sticky or time in milliseconds to hide after
        stack: 5,                     // `false` to show one stack at a time count showing the number of toasts that can be shown at once
        textAlign: 'left',            // Alignment of text i.e. left, right, center
        position: 'top-right'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
    })
}

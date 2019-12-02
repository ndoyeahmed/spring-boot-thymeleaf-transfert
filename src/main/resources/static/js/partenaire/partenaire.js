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

            let json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            // $("#btn-search").prop("disabled", false);

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
                json = json +
                    "<tr>" +
                    "<td>" + p.ninea + "</td>" +
                    "<td>" + p.nom + "</td>" +
                    "<td>" + p.tel + "</td>" +
                    "<td>" + p.mail + "</td>" +
                    "<td>" + p.adresse + "</td>" +
                    "</tr>"
            }

            json = json + "</tbody>" +
                "                        </table>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            // $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            /* let json = "<h4>Ajax Response</h4><pre>"
                 + e.responseText + "</pre>";
             $('#feedback').html(json);

             console.log("ERROR : ", e);*/
            // $("#btn-search").prop("disabled", false);

        }
    });
}

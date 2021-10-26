
$(document).ready( function () {
    var table = $('#orderTable').DataTable({
        "sAjaxSource": "/admin",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" },
            { "mData": "email" },
            { "mData": "phone" },
            { "mData": "active" }
        ]
    })
});
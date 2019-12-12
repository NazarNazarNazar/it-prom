const url = "/api/employees/";
const professionsNameUrl = "/api/professions/name";
const professionOptions = $("#profession");
const form = $(".employee-details-form");
const modalTitle = $(".modal-title-label");
const modal = $(".add-employee-modal");
const table = $("#employeeTable");

let datatableEmployeeApi;

$(function () {
    getProfessionsName();
    datatableEmployeeApi = table.DataTable({
        "paging": false,
        "info": false,
        "filter": false,
        "ajax": {
            url: url,
            dataSrc: '',
            method: 'GET'
        },
        "columns": [
            {data: "id"},
            {data: "firstName"},
            {data: "secondName"},
            {data: "patronymic"},
            {data: "profession"},
            {data: "department"},
            {data: "note"},
            {
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderDeleteBtn,
                "defaultContent": "",
                "orderable": false
            }
        ]
    })
});

function updateRow(id) {
    modalTitle.html("Edit");
    $.get(url + id, function (data) {
        $.each(data, function (key, value) {
            $('.employee-details-form #' + key + '').val(value);
        });
        modal.modal();
    });

}

function getProfessionsName() {
    professionOptions.prepend('<option value="">');
    $.get(professionsNameUrl, function (data) {
        $.each(data, function (key, value) {
            professionOptions.append(new Option(value, value));
        })
    });
}

function save() {
    let data = form.serializeArray()
        .reduce(function(a, x) { a[x.name] = x.value; return a; }, {});
    console.log(data);
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data)
    }).done(function () {
        modal.modal("hide");
        updateTable();
    })
}

function add() {
    modalTitle.html("Add");
    form.find(":input").val("");
    modal.modal();
}

function updateTableByData(data) {
    datatableEmployeeApi.clear().rows.add(data).draw();
}

function deleteRow(id) {
    $.ajax({
        url: url + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
    });
}

function updateTable() {
    $.ajax({
        type: "GET",
        url: url,
        dataSrc: ''
    }).done(updateTableByData);
}

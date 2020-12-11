
const LEYTE_ID_INDEX = 0;
const SOUTHERN_LEYTE_ID_INDEX = 1;
const BILIRAN_ID_INDEX = 2;
const SAMAR_ID_INDEX = 3;
const EASTERN_SAMAR_ID_INDEX = 4;
const NORTHERN_SAMAR_ID_INDEX = 5;

const twspDataLabel = "twspData";
const pesfaDataLabel = "pesfaData";
const stepDataLabel = "stepData";
const uaqteaSbLabel = "uaqteaSbData";
const uaqteaDiplomaLabel = "uaqteaDiplomaData";

/* Derived from SPMOR google sheet for UAQTEA summary
*
    sbId refers to the id in the json retrieved, containing the UAQTEA-SB row
    diplomaId refers to the id in the json retrieved, containing the UAQTEA-DIPLOMA row
    *   99 is default value for non-existing uaqtea-diploma row
    idIndex refers to the id in the input field from the form, on which the value would be updated
*/
const operatingUnitList = [
    {name: 'Biliran', sbId: 13, diplomaId: 24, idIndex: 2},
    {name: 'Eastern Samar', sbId: 14, diplomaId: 25, idIndex: 4},
    {name: 'Leyte', sbId: 15, diplomaId: 26, idIndex: 0},
    {name: 'Northern Samar', sbId: 16, diplomaId: 27, idIndex: 5},
    {name: 'Samar', sbId: 17, diplomaId: 99, idIndex: 3},
    {name: 'Southern Leyte', sbId: 18, diplomaId: 99, idIndex: 1}
];

window.onload = function () {
    document.getElementById('submitButton1').disabled=false;
    $("#submitButton1").click(function(event) {
        if($('#updateScholarshipForm').valid()) {
            event.preventDefault();
            var currentElement = $(this);
            swal({
                title: "Save Scholarship Accomplishment Live Data?",
                text: "Kindly check the details before saving.",
                icon: "warning",
                buttons: ["No", "Yes"],
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        swal("Scholarship Accomplishment Live Data successfully saved!", {
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


// TWSP
    const twspTableIndexes = ["gsx$_b2zk2", "gsx$_ewna8", "gsx$_axdae", "gsx$_bmnjo", "gsx$_c23sb", "gsx$_c7q1z", "gsx$_hmyxm", "gsx$_fc3dj", "gsx$_4esbz", "gsx$_j9ysw"];
    const twspJsonIndex = 1
    // BILIRAN
    fetchSPMORData("1Tu_oi0WeeNSfOejqluKEAiLl496L_A3GLZ4MANOzVyQ",  twspTableIndexes, twspJsonIndex, twspDataLabel, BILIRAN_ID_INDEX);
    // EASTERN SAMAR
    fetchSPMORData("1HT1SWttx18doMy-ubAEXvckNlb43g7qXhM7XI7-We4M",  twspTableIndexes, twspJsonIndex, twspDataLabel, EASTERN_SAMAR_ID_INDEX);
    // LEYTE
    fetchSPMORData("1UAWFh3r4uuhrfO7r82UovqWwkFwxDRRCjoV4fSC34i4", twspTableIndexes, twspJsonIndex, twspDataLabel, LEYTE_ID_INDEX);
    // NORTHERN SAMAR
    fetchSPMORData("1tcaa-Rfasm2iEzBLQQKwq2xV30kKZUgtCm1b5DjbKq4", twspTableIndexes, twspJsonIndex, twspDataLabel, NORTHERN_SAMAR_ID_INDEX);
    // Samar
    fetchSPMORData("15Sk3JLUC4Elym0UR2IYKCRMIcniqUcoWfVJ0MZ8eVJQ",  twspTableIndexes, twspJsonIndex, twspDataLabel, SAMAR_ID_INDEX);
    // SOUTHERN LEYTE
    fetchSPMORData("1wfqDi7mrTSjSptbTMBPoJVNklpIJmZBv-i14X6oKMI8",  twspTableIndexes, twspJsonIndex, twspDataLabel, SOUTHERN_LEYTE_ID_INDEX);

// PESFA
    const pesfaTableIndexes = ["gsx$_jo0h2", "gsx$_it3zw", "gsx$_js86d", "gsx$_fkis1", "gsx$_flxce", "gsx$_ge0qi", "gsx$_gi8ft", "gsx$_emtbd", "gsx$_bxw30", "gsx$_dgo93"];
    const pesfaJsonIndex = 2;
    // BILIRAN
    fetchSPMORData("1Ycz8LjsYhZP5t0tqqagnHJKymASrR_XnAdcpuhsBIZU",  pesfaTableIndexes, pesfaJsonIndex, pesfaDataLabel, BILIRAN_ID_INDEX);
    // EASTERN SAMAR
    fetchSPMORData("1krJa1Uogf3XR-333jfku4KZM-RZOXqbS_C7wOVmrivk",  pesfaTableIndexes, pesfaJsonIndex, pesfaDataLabel, EASTERN_SAMAR_ID_INDEX);
    // LEYTE
    fetchSPMORData("1eFL8JtWN4THVYLi-HJlkwLkkCAUB-11ZAQ9Jfp3ZlPo",  pesfaTableIndexes, pesfaJsonIndex, pesfaDataLabel, LEYTE_ID_INDEX);
    // NORTHERN SAMAR
    fetchSPMORData("1eXgaqDwhwOxYqZVC4cLmicHW74E86eIeYOKmMAWBdUs", pesfaTableIndexes, pesfaJsonIndex, pesfaDataLabel, NORTHERN_SAMAR_ID_INDEX);
    // Samar
    fetchSPMORData("1j5-qH-5iPzYL_7RpmhMWIcFmFZdDLKcR8D99r1inTQk",  pesfaTableIndexes, pesfaJsonIndex, pesfaDataLabel, SAMAR_ID_INDEX);
    // SOUTHERN LEYTE
    fetchSPMORData("1_X2C4jCEfVav1aLxwA9Z-4iI9UGtQ1jDtc_HW4EJKD4",  pesfaTableIndexes, pesfaJsonIndex, pesfaDataLabel, SOUTHERN_LEYTE_ID_INDEX);

// STEP
    const stepTableIndexes = ["gsx$_arr0q", "gsx$_ev8pv", "gsx$_aoxvw", "gsx$_bh1a0", "gsx$_btodt", "gsx$_bzanh", "gsx$_ceqw0", "gsx$_jie7e", "gsx$_guvji", "gsx$_inhog"];
    const stepJsonIndex = 2;
    // BILIRAN
    fetchSPMORData("1S-wiZWLypmv_PAI56PBQaD0j6qkBc4ZBOeNtVy9erb0",  stepTableIndexes, stepJsonIndex, stepDataLabel, BILIRAN_ID_INDEX);
    // EASTERN SAMAR
    fetchSPMORData("1N90o8oJDy5Ht2LGVSI-aabjD__GB5CSeqi-goBY4EV8",  stepTableIndexes, stepJsonIndex, stepDataLabel, EASTERN_SAMAR_ID_INDEX);
    // LEYTE
    fetchSPMORData("16PkVWiVTa3zzB9hKeXhFEbejzHhtaW0A3yOB5uMg0Rk",  stepTableIndexes, stepJsonIndex, stepDataLabel, LEYTE_ID_INDEX);
    // NORTHERN SAMAR
    fetchSPMORData("1jOE6sj10lzEEjhS1CcEWFIFb9I1ErZKkYRsd34tFWBI",  stepTableIndexes, stepJsonIndex, stepDataLabel, NORTHERN_SAMAR_ID_INDEX);
    // Samar
    fetchSPMORData("1HoFy0h6TqRrw0zUZ5JlRk05eMTFb6QHpGqDTzRIHUII", stepTableIndexes, stepJsonIndex, stepDataLabel, SAMAR_ID_INDEX);
    // SOUTHERN LEYTE
    fetchSPMORData("1ODMU59l0J_HTeSx3EKpqHQvV3-TDl0qmQX4zhkZ8lgo", stepTableIndexes, stepJsonIndex, stepDataLabel, SOUTHERN_LEYTE_ID_INDEX);

// UAQTEA-SB and UAQTEA-DIPLOMA
    const uaqteaTableIndexes = ["gsx$_cpzh4", "gsx$_cokwr", "gsx$_cre1l", "gsx$_chk2m", "gsx$_ciyn3", "gsx$_ckd7g", "gsx$_clrrx", "gsx$_cztg3", "gsx$_d180g"];
    fetchSPMORUaqteaData("1VWWWEeBs9IShM-En7G-idPuDw7dneddhcuJ4J9Hj4yk", uaqteaTableIndexes);
}


function replaceChars(value) {
    let updatedString = value.replace(/,/g, "");
    updatedString = updatedString.trim();
    return updatedString.replace(/-/g, "0");
}


function fetchSPMORData(sheetId, tableIndexes, jsonIndex, dataLabel, idIndex) {
    $.getJSON("https://spreadsheets.google.com/feeds/list/" + sheetId + "/1/public/values?alt=json", function (data) {

        const index = jsonIndex;

        const amount = replaceChars(data.feed.entry[index][tableIndexes[0]]['$t']);
        const amountId = dataLabel + idIndex + ".qualificationMapDto.amount";
        document.getElementById(amountId).value = amount;

        const slots = replaceChars(data.feed.entry[index][tableIndexes[1]]['$t']);
        const slotsId = dataLabel + idIndex + ".qualificationMapDto.slots";
        document.getElementById(slotsId).value = slots;

        const enrolled = replaceChars(data.feed.entry[index][tableIndexes[2]]['$t']);
        const enrolledId = dataLabel + idIndex + ".physicalAccomplishmentDto.enrolled";
        document.getElementById(enrolledId).value = enrolled;

        const graduates = replaceChars(data.feed.entry[index][tableIndexes[3]]['$t']);
        const graduatesId = dataLabel + idIndex + ".physicalAccomplishmentDto.graduates";
        document.getElementById(graduatesId).value = graduates;

        const assessed = replaceChars(data.feed.entry[index][tableIndexes[4]]['$t']);
        const assessedId = dataLabel + idIndex + ".physicalAccomplishmentDto.assessed";
        document.getElementById(assessedId).value = assessed;

        const certified = replaceChars(data.feed.entry[index][tableIndexes[5]]['$t']);
        const certifiedId = dataLabel + idIndex + ".physicalAccomplishmentDto.certified";
        document.getElementById(certifiedId).value = certified;

        const employed = replaceChars(data.feed.entry[index][tableIndexes[6]]['$t']);
        const employedId = dataLabel + idIndex + ".physicalAccomplishmentDto.employed";
        document.getElementById(employedId).value = employed;

        const totalObligation = replaceChars(data.feed.entry[index][tableIndexes[7]]['$t']);
        const totalObligationId = dataLabel + idIndex + ".financialAccomplishmentDto.poFinancialAccomplishmentDto.totalObligation";
        document.getElementById(totalObligationId).value = totalObligation;

        const totalDisbursement = replaceChars(data.feed.entry[index][tableIndexes[8]]['$t']);
        const totalDisbursementId = dataLabel + idIndex + ".financialAccomplishmentDto.poFinancialAccomplishmentDto.totalDisbursement";
        document.getElementById(totalDisbursementId).value = totalDisbursement;

        const adaAmount = replaceChars(data.feed.entry[index][tableIndexes[9]]['$t']);
        const adaAmountId = dataLabel + idIndex + ".financialAccomplishmentDto.roFinancialAccomplishmentDto.adaAmount";
        document.getElementById(adaAmountId).value = adaAmount;
    });
}

function fetchSPMORUaqteaData(sheetId, tableIndexes) {
    $.getJSON("https://spreadsheets.google.com/feeds/list/" + sheetId + "/1/public/values?alt=json", function (data) {
        var sheetData = data.feed.entry;
        var index;
        for (index = 13; index < 28; index++) {
            for (var key = 0; key < operatingUnitList.length; key++) {
                if (operatingUnitList[key].sbId === index) {
                    updateInputValues(tableIndexes, uaqteaSbLabel, operatingUnitList[key].idIndex, sheetData, index);
                } else if (operatingUnitList[key].diplomaId === index) {
                    updateInputValues(tableIndexes, uaqteaDiplomaLabel, operatingUnitList[key].idIndex, sheetData, index);
                }
            }
        }
    });
}

function updateInputValues(tableIndexes, dataLabel, idIndex, sheetData, sheetIndex) {
    const amount = replaceChars(sheetData[sheetIndex][tableIndexes[0]]['$t']);
    const amountId = dataLabel + idIndex + ".qualificationMapDto.amount";
    document.getElementById(amountId).value = amount;

    const slots = replaceChars(sheetData[sheetIndex][tableIndexes[1]]['$t']);
    const slotsId = dataLabel + idIndex + ".qualificationMapDto.slots";
    document.getElementById(slotsId).value = slots;

    const enrolled = replaceChars(sheetData[sheetIndex][tableIndexes[2]]['$t']);
    const enrolledId = dataLabel + idIndex + ".physicalAccomplishmentDto.enrolled";
    document.getElementById(enrolledId).value = enrolled;

    const graduates = replaceChars(sheetData[sheetIndex][tableIndexes[3]]['$t']);
    const graduatesId = dataLabel + idIndex + ".physicalAccomplishmentDto.graduates";
    document.getElementById(graduatesId).value = graduates;

    const assessed = replaceChars(sheetData[sheetIndex][tableIndexes[4]]['$t']);
    const assessedId = dataLabel + idIndex + ".physicalAccomplishmentDto.assessed";
    document.getElementById(assessedId).value = assessed;

    const certified = replaceChars(sheetData[sheetIndex][tableIndexes[5]]['$t']);
    const certifiedId = dataLabel + idIndex + ".physicalAccomplishmentDto.certified";
    document.getElementById(certifiedId).value = certified;

    const employed = replaceChars(sheetData[sheetIndex][tableIndexes[6]]['$t']);
    const employedId = dataLabel + idIndex + ".physicalAccomplishmentDto.employed";
    document.getElementById(employedId).value = employed;

    const totalObligation = replaceChars(sheetData[sheetIndex][tableIndexes[7]]['$t']);
    const totalObligationId = dataLabel + idIndex + ".financialAccomplishmentDto.poFinancialAccomplishmentDto.totalObligation";
    document.getElementById(totalObligationId).value = totalObligation;

    const totalDisbursement = replaceChars(sheetData[sheetIndex][tableIndexes[8]]['$t']);
    const totalDisbursementId = dataLabel + idIndex + ".financialAccomplishmentDto.poFinancialAccomplishmentDto.totalDisbursement";
    document.getElementById(totalDisbursementId).value = totalDisbursement;
}


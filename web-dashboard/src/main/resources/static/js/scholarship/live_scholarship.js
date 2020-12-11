/* Derived from SPMOR google sheet for UAQTEA summary
*
    sbId refers to the id in the json retrieved, containing the UAQTEA-SB row
    diplomaId refers to the id in the json retrieved, containing the UAQTEA-DIPLOMA row
    *   99 is default value for non-existing uaqtea-diploma row
*/
const operatingUnitList = [
    {name: 'Biliran', sbId: 13, diplomaId: 24},
    {name: 'Eastern Samar', sbId: 14, diplomaId: 25},
    {name: 'Leyte', sbId: 15, diplomaId: 26},
    {name: 'Northern Samar', sbId: 16, diplomaId: 27},
    {name: 'Samar', sbId: 17, diplomaId: 99},
    {name: 'Southern Leyte', sbId: 18, diplomaId: 99}
];


window.onload = function () {
// TWSP
    const twspTableIndexes = ["gsx$_b2zk2", "gsx$_ewna8", "gsx$_axdae", "gsx$_bmnjo", "gsx$_c23sb", "gsx$_c7q1z", "gsx$_hmyxm", "gsx$_fc3dj", "gsx$_4esbz", "gsx$_j9ysw"];
    const twspJsonIndex = 1
    // BILIRAN
    fetchSPMORData("1Tu_oi0WeeNSfOejqluKEAiLl496L_A3GLZ4MANOzVyQ", "Biliran", "twsp-tbody", twspTableIndexes, twspJsonIndex);
    // EASTERN SAMAR
    fetchSPMORData("1HT1SWttx18doMy-ubAEXvckNlb43g7qXhM7XI7-We4M", "Eastern Samar", "twsp-tbody", twspTableIndexes, twspJsonIndex);
    // LEYTE
    fetchSPMORData("1UAWFh3r4uuhrfO7r82UovqWwkFwxDRRCjoV4fSC34i4", "Leyte", "twsp-tbody", twspTableIndexes, twspJsonIndex);
    // NORTHERN SAMAR
    fetchSPMORData("1tcaa-Rfasm2iEzBLQQKwq2xV30kKZUgtCm1b5DjbKq4", "Northern Samar", "twsp-tbody", twspTableIndexes, twspJsonIndex);
    // Samar
    fetchSPMORData("15Sk3JLUC4Elym0UR2IYKCRMIcniqUcoWfVJ0MZ8eVJQ", "Samar", "twsp-tbody", twspTableIndexes, twspJsonIndex);
    // SOUTHERN LEYTE
    fetchSPMORData("1wfqDi7mrTSjSptbTMBPoJVNklpIJmZBv-i14X6oKMI8", "Southern Leyte", "twsp-tbody", twspTableIndexes, twspJsonIndex);

// PESFA
    const pesfaTableIndexes = ["gsx$_jo0h2", "gsx$_it3zw", "gsx$_js86d", "gsx$_fkis1", "gsx$_flxce", "gsx$_ge0qi", "gsx$_gi8ft", "gsx$_emtbd", "gsx$_bxw30", "gsx$_dgo93"];
    const pesfaJsonIndex = 2;
    // BILIRAN
    fetchSPMORData("1Ycz8LjsYhZP5t0tqqagnHJKymASrR_XnAdcpuhsBIZU", "Biliran", "pesfa-tbody", pesfaTableIndexes, pesfaJsonIndex);
    // EASTERN SAMAR
    fetchSPMORData("1krJa1Uogf3XR-333jfku4KZM-RZOXqbS_C7wOVmrivk", "Eastern Samar", "pesfa-tbody", pesfaTableIndexes, pesfaJsonIndex);
    // LEYTE
    fetchSPMORData("1eFL8JtWN4THVYLi-HJlkwLkkCAUB-11ZAQ9Jfp3ZlPo", "Leyte", "pesfa-tbody", pesfaTableIndexes, pesfaJsonIndex);
    // NORTHERN SAMAR
    fetchSPMORData("1eXgaqDwhwOxYqZVC4cLmicHW74E86eIeYOKmMAWBdUs", "Northern Samar", "pesfa-tbody", pesfaTableIndexes, pesfaJsonIndex);
    // Samar
    fetchSPMORData("1j5-qH-5iPzYL_7RpmhMWIcFmFZdDLKcR8D99r1inTQk", "Samar", "pesfa-tbody", pesfaTableIndexes, pesfaJsonIndex);
    // SOUTHERN LEYTE
    fetchSPMORData("1_X2C4jCEfVav1aLxwA9Z-4iI9UGtQ1jDtc_HW4EJKD4", "Southern Leyte", "pesfa-tbody", pesfaTableIndexes, pesfaJsonIndex);

// STEP
    const stepTableIndexes = ["gsx$_arr0q", "gsx$_ev8pv", "gsx$_aoxvw", "gsx$_bh1a0", "gsx$_btodt", "gsx$_bzanh", "gsx$_ceqw0", "gsx$_jie7e", "gsx$_guvji", "gsx$_inhog"];
    const stepJsonIndex = 2;
    // BILIRAN
    fetchSPMORData("1S-wiZWLypmv_PAI56PBQaD0j6qkBc4ZBOeNtVy9erb0", "Biliran", "step-tbody", stepTableIndexes, stepJsonIndex);
    // EASTERN SAMAR
    fetchSPMORData("1N90o8oJDy5Ht2LGVSI-aabjD__GB5CSeqi-goBY4EV8", "Eastern Samar", "step-tbody", stepTableIndexes, stepJsonIndex);
    // LEYTE
    fetchSPMORData("16PkVWiVTa3zzB9hKeXhFEbejzHhtaW0A3yOB5uMg0Rk", "Leyte", "step-tbody", stepTableIndexes, stepJsonIndex);
    // NORTHERN SAMAR
    fetchSPMORData("1jOE6sj10lzEEjhS1CcEWFIFb9I1ErZKkYRsd34tFWBI", "Northern Samar", "step-tbody", stepTableIndexes, stepJsonIndex);
    // Samar
    fetchSPMORData("1HoFy0h6TqRrw0zUZ5JlRk05eMTFb6QHpGqDTzRIHUII", "Samar", "step-tbody", stepTableIndexes, stepJsonIndex);
    // SOUTHERN LEYTE
    fetchSPMORData("1ODMU59l0J_HTeSx3EKpqHQvV3-TDl0qmQX4zhkZ8lgo", "Southern Leyte", "step-tbody", stepTableIndexes, stepJsonIndex);

// UAQTEA-SB and UAQTEA-DIPLOMA
    const uaqteaTableIndexes = ["gsx$_cpzh4", "gsx$_cokwr", "gsx$_cre1l", "gsx$_chk2m", "gsx$_ciyn3", "gsx$_ckd7g", "gsx$_clrrx", "gsx$_cztg3", "gsx$_d180g"];
    fetchSPMORDataUAQTEA("1VWWWEeBs9IShM-En7G-idPuDw7dneddhcuJ4J9Hj4yk", uaqteaTableIndexes);

}


function fetchSPMORData(sheetId, provinceName, tableBodyId, tableIndexes, jsonIndex) {
    $.getJSON("https://spreadsheets.google.com/feeds/list/" + sheetId + "/1/public/values?alt=json", function (data) {

        const index = jsonIndex;
        console.log(data.feed.entry[index]);
        const province = provinceName;

        const amount = data.feed.entry[index][tableIndexes[0]]['$t'];
        const slots = data.feed.entry[index][tableIndexes[1]]['$t'];

        const enrolled = data.feed.entry[index][tableIndexes[2]]['$t'];
        const graduates = data.feed.entry[index][tableIndexes[3]]['$t'];
        const assessed = data.feed.entry[index][tableIndexes[4]]['$t'];
        const certified = data.feed.entry[index][tableIndexes[5]]['$t'];
        const employed = data.feed.entry[index][tableIndexes[6]]['$t'];

        const totalObligation = data.feed.entry[index][tableIndexes[7]]['$t'];
        const totalDisbursement = data.feed.entry[index][tableIndexes[8]]['$t'];
        const adaAmount = data.feed.entry[index][tableIndexes[9]]['$t'];

        const utilizationRate = calculateRate(replaceChars(enrolled), replaceChars(slots));
        const completionRate = calculateRate(replaceChars(graduates), replaceChars(enrolled));
        const assessmentRate = calculateRate(replaceChars(assessed), replaceChars(graduates));
        const certificationRate = calculateRate(replaceChars(certified), replaceChars(assessed));

        const obligationRate = calculateRate(replaceChars(totalObligation), replaceChars(adaAmount));
        const disbursementRate = calculateRate(replaceChars(totalDisbursement), replaceChars(totalObligation));

        document.getElementById(tableBodyId).innerHTML += ('<tr>'
            +'<td>'+province+'</td>'
            +'<td><span class="strong-data">'+amount+'</span></td>'
            +'<td><span class="strong-data">'+slots+'</span></td>'
            +'<td class="col-striped"><span class="strong-data">'+adaAmount+'</span></td>'
            +'<td class="col-striped"><span class="strong-data">'+totalObligation+'</span></td>'
            +'<td class="col-striped">'+obligationRate+'%</td>'
            +'<td class="col-striped"><span class="strong-data">'+totalDisbursement+'</span></td>'
            +'<td class="col-striped">'+disbursementRate+'%</td>'
            +'<td class="col-striped2"><span class="strong-data">'+enrolled+'</span></td>'
            +'<td class="col-striped2">'+utilizationRate+'%</td>'
            +'<td class="col-striped2"><span class="strong-data">'+graduates+'</span></td>'
            +'<td class="col-striped2">'+completionRate+'%</td>'
            +'<td class="col-striped2"><span class="strong-data">'+assessed+'</span></td>'
            +'<td class="col-striped2">'+assessmentRate+'%</td>'
            +'<td class="col-striped2"><span class="strong-data">'+certified+'</span></td>'
            +'<td class="col-striped2">'+certificationRate+'%</td>'
            +'<td class="col-striped2"><span class="strong-data">'+employed+'</span></td>'
            +'</tr>');
    });
}


function fetchSPMORDataUAQTEA(sheetId, tableIndexes) {
    $.getJSON("https://spreadsheets.google.com/feeds/list/" + sheetId + "/1/public/values?alt=json", function (data) {
        var sheetData = data.feed.entry;
        var index;
        for (index = 13; index < 28; index++) {
            for (var key = 0; key < operatingUnitList.length; key++) {
                if (operatingUnitList[key].sbId === index) {
                    addUAQTEARows(operatingUnitList[key].name, index, tableIndexes, 'uaqtea-sb-tbody', sheetData)
                } else if (operatingUnitList[key].diplomaId === index) {
                    addUAQTEARows(operatingUnitList[key].name, index, tableIndexes, 'uaqtea-diploma-tbody', sheetData)
                }
            }
        }
    });
}

function addUAQTEARows(provinceName, index, tableIndexes, tableBodyId, sheetData) {
    const province = provinceName;

    const amount = sheetData[index][tableIndexes[0]]['$t'];
    const slots = sheetData[index][tableIndexes[1]]['$t'];

    const enrolled = sheetData[index][tableIndexes[2]]['$t'];
    const graduates = sheetData[index][tableIndexes[3]]['$t'];
    const assessed = sheetData[index][tableIndexes[4]]['$t'];
    const certified = sheetData[index][tableIndexes[5]]['$t'];
    const employed = sheetData[index][tableIndexes[6]]['$t'];

    const totalObligation = sheetData[index][tableIndexes[7]]['$t'];
    const totalDisbursement = sheetData[index][tableIndexes[8]]['$t'];

    const utilizationRate = calculateRate(replaceChars(enrolled), replaceChars(slots));
    const completionRate = calculateRate(replaceChars(graduates), replaceChars(enrolled));
    const assessmentRate = calculateRate(replaceChars(assessed), replaceChars(graduates));
    const certificationRate = calculateRate(replaceChars(certified), replaceChars(assessed));

    const obligationRate = "";
    const disbursementRate = calculateRate(replaceChars(totalDisbursement), replaceChars(totalObligation));

    document.getElementById(tableBodyId).innerHTML += ('<tr>'
        +'<td>'+province+'</td>'
        +'<td><span class="strong-data">'+amount+'</span></td>'
        +'<td><span class="strong-data">'+slots+'</span></td>'
        +'<td>'+'</td>'
        +'<td class="col-striped"><span class="strong-data">'+totalObligation+'</span></td>'
        +'<td class="col-striped">'+obligationRate+'%</td>'
        +'<td class="col-striped"><span class="strong-data">'+totalDisbursement+'</span></td>'
        +'<td class="col-striped">'+disbursementRate+'%</td>'
        +'<td class="col-striped2"><span class="strong-data">'+enrolled+'</span></td>'
        +'<td class="col-striped2">'+utilizationRate+'%</td>'
        +'<td class="col-striped2"><span class="strong-data">'+graduates+'</span></td>'
        +'<td class="col-striped2">'+completionRate+'%</td>'
        +'<td class="col-striped2"><span class="strong-data">'+assessed+'</span></td>'
        +'<td class="col-striped2">'+assessmentRate+'%</td>'
        +'<td class="col-striped2"><span class="strong-data">'+certified+'</span></td>'
        +'<td class="col-striped2">'+certificationRate+'%</td>'
        +'<td class="col-striped2"><span class="strong-data">'+employed+'</span></td>'
        +'</tr>');
}

function calculateRate(dividend, divisor) {
    return numberWithCommas((100 * (dividend / divisor)).toFixed(2));
}

function numberWithCommas(x) {
    console.log(x);
    if (isNaN(x)) {
        return 0.00;
    }
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function replaceChars(value) {
    let updatedString = value.replace(/,/g, "");
    updatedString = updatedString.trim();
    return updatedString.replace(/-/g, "0");
}
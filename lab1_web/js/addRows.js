function addRows(rowsJSON) {
    let rows = JSON.parse(rowsJSON);
    if (!document.body.contains(document.getElementById('answers')))
        drawTable();
    rows.forEach(function (row) {
        let newRow = document.createElement('tr');
        let newRowX = document.createElement('td');
        let newRowY = document.createElement('td');
        let newRowR = document.createElement('td');
        let newRowTime = document.createElement('td');
        let newRowEnd = document.createElement('td');
        let newRowResult = document.createElement('td');
        newRowX.innerHTML = row.x;
        newRowY.innerHTML = row.y;
        newRowR.innerHTML = row.r;
        newRowTime.innerHTML = row.time;
        newRowEnd.innerHTML = row.end;
        newRowResult.innerHTML = row.result;
        newRow.appendChild(newRowX);
        newRow.appendChild(newRowY);
        newRow.appendChild(newRowR);
        newRow.appendChild(newRowTime);
        newRow.appendChild(newRowEnd);
        newRow.appendChild(newRowResult);
        document.getElementById('answers body').appendChild(newRow);
    });
}
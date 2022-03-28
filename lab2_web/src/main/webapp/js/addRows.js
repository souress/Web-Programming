function addRows(row) {
    if (!document.body.contains(document.getElementById('answers')))
        drawTable();
    let newRow = document.createElement('tr');
    let newRowX = document.createElement('td');
    let newRowY = document.createElement('td');
    let newRowR = document.createElement('td');
    let newRowTime = document.createElement('td');
    let newRowDuration = document.createElement('td');
    let newRowResult = document.createElement('td');
    newRowX.innerHTML = row.x;
    newRowY.innerHTML = row.y;
    newRowR.innerHTML = row.r;
    newRowTime.innerHTML = row.currentTime;
    newRowDuration.innerHTML = row.duration + " пс";
    newRowResult.innerHTML = row.result;
    newRow.appendChild(newRowX);
    newRow.appendChild(newRowY);
    newRow.appendChild(newRowR);
    newRow.appendChild(newRowTime);
    newRow.appendChild(newRowDuration);
    newRow.appendChild(newRowResult);
    document.getElementById('answers body').appendChild(newRow);
}
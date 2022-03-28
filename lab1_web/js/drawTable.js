function drawTable() {
    let table = document.createElement('table');
    table.setAttribute('border', '2');
    table.setAttribute('align', 'center');
    table.setAttribute('id', 'answers');
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');
    tbody.setAttribute('id', 'answers body');

    table.appendChild(thead);
    table.appendChild(tbody);

    let header = document.createElement('tr');
    let headX = document.createElement('th');
    headX.innerHTML = "Значение X";
    let headY = document.createElement('th');
    headY.innerHTML = "Значение Y";
    let headR = document.createElement('th');
    headR.innerHTML = "Значение R";
    let headStart = document.createElement('th');
    headStart.innerHTML = "Текущее время";
    let headEnd = document.createElement('th');
    headEnd.innerHTML = "Время работы скрипта";
    let headAnswer = document.createElement('th');

    headAnswer.innerHTML = "Попадание";
    header.appendChild(headX);
    header.appendChild(headY);
    header.appendChild(headR);
    header.appendChild(headStart);
    header.appendChild(headEnd);
    header.appendChild(headAnswer);

    thead.appendChild(header);

    document.getElementById('body').appendChild(document.createElement('br'));
    document.getElementById('body').appendChild(table);
}
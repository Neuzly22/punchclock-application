const URL = 'http://localhost:8081';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};
const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};
const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};
const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};
const appendButtonToCell = (index) => {
    const button = document.createElement('button');
    const cell = document.createElement('td');
    button.innerText = "delete";
    button.addEventListener("click", () => deleteCell(index));
    cell.appendChild(button);
    return cell;
}

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.map((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(appendButtonToCell(entry.id))
        display.appendChild(row);
        return entry;
    });
};
const deleteCell = (index) => {
    fetch(`${URL}/entries/${index}`,
        {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
        }
    ).then(response => {
        if (response.status === 200) {
            entries = entries.filter(entry => entry.id !== index)
            indexEntries();
        }
    })
}
document.addEventListener('DOMContentLoaded', function () {
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});
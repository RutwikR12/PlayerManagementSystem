const API_BASE = 'http://localhost:8080';

// Fetch and display all players on load
async function fetchPlayers() {
    try {
        const response = await fetch(`${API_BASE}/players`);
        if (!response.ok) throw new Error('Failed to fetch players');
        const players = await response.json();
        renderTable(players);
    } catch (error) {
        console.error(error);
        document.getElementById('playerTableBody').innerHTML = `<tr><td colspan="6" class="p-4 text-center text-red-500">Could not connect to backend server. Make sure Spring Boot is running!</td></tr>`;
    }
}

// Handle dynamic search based on backend endpoints (/name/{name} and /sport/{sport})
async function handleSearch() {
    const searchType = document.getElementById('searchType').value;
    const query = document.getElementById('searchInput').value.trim();

    if (searchType === 'all' || query === '') {
        fetchPlayers();
        return;
    }

    let endpoint = '';
    if (searchType === 'name') {
        endpoint = `${API_BASE}/name/${encodeURIComponent(query)}`;
    } else if (searchType === 'sport') {
        endpoint = `${API_BASE}/sport/${encodeURIComponent(query)}`;
    }

    try {
        const response = await fetch(endpoint);
        const tbody = document.getElementById('playerTableBody');
        
        if (response.ok) {
            const players = await response.json();
            renderTable(players);
        } else {
            // Catches exceptions returned by Global Exception Handler (e.g. Player not found)
            const errorMsg = await response.text();
            tbody.innerHTML = `<tr><td colspan="6" class="p-6 text-center text-amber-600 font-medium">${errorMsg}</td></tr>`;
        }
    } catch (err) {
        console.error(err);
    }
}

// Render table rows
function renderTable(players) {
    const tbody = document.getElementById('playerTableBody');
    tbody.innerHTML = '';
    
    if (!players || players.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6" class="p-6 text-center text-slate-400">No players found.</td></tr>`;
        return;
    }

    players.forEach(p => {
        tbody.innerHTML += `
            <tr class="hover:bg-slate-50">
                <td class="p-3 font-medium">#${p.id}</td>
                <td class="p-3">
                    <div class="font-semibold text-slate-900">${p.name}</div>
                    <div class="text-xs text-slate-400">Age: ${p.age}</div>
                </td>
                <td class="p-3"><span class="bg-indigo-50 text-indigo-700 text-xs px-2.5 py-1 rounded-full font-semibold">${p.sport}</span></td>
                <td class="p-3 text-xs text-slate-600">
                    <div>${p.email}</div>
                    <div>${p.mobileNo}</div>
                </td>
                <td class="p-3 text-xs text-slate-600">${p.city}, ${p.state}</td>
                <td class="p-3 text-center">
                    <button onclick="deletePlayer(${p.id})" class="text-red-500 hover:text-red-700 text-xs font-semibold px-2 py-1 bg-red-50 rounded hover:bg-red-100 transition">Delete</button>
                </td>
            </tr>
        `;
    });
}

// Handle Add Player Form submission
document.getElementById('playerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const msgDiv = document.getElementById('formMessage');
    
    const playerDto = {
        name: document.getElementById('name').value,
        age: parseInt(document.getElementById('age').value),
        dateOfBirth: document.getElementById('dateOfBirth').value,
        email: document.getElementById('email').value,
        mobileNo: document.getElementById('mobileNo').value,
        city: document.getElementById('city').value,
        state: document.getElementById('state').value,
        sport: document.getElementById('sport').value
    };

    try {
        const response = await fetch(`${API_BASE}/player`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(playerDto)
        });

        const resultText = await response.text();

        if (response.ok) {
            msgDiv.className = "mt-4 text-xs text-center font-medium text-green-600";
            msgDiv.textContent = resultText;
            document.getElementById('playerForm').reset();
            fetchPlayers();
        } else {
            msgDiv.className = "mt-4 text-xs text-center font-medium text-red-600";
            msgDiv.textContent = resultText; 
        }
    } catch (err) {
        msgDiv.className = "mt-4 text-xs text-center font-medium text-red-600";
        msgDiv.textContent = "Error communicating with server.";
    }
});

// Delete Player
async function deletePlayer(id) {
    if (!confirm(`Are you sure you want to delete player #${id}?`)) return;
    try {
        const response = await fetch(`${API_BASE}/delete/${id}`, { method: 'DELETE' });
        if (response.ok) {
            fetchPlayers();
        } else {
            alert('Failed to delete player');
        }
    } catch (err) {
        console.error(err);
    }
}

// Initial fetch on page load
fetchPlayers();
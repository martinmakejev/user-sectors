export const addUser = async (name, selectedSectorsIds, terms) => {
    try {
        const res = await fetch('http://localhost:8080/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name,
                sectors: selectedSectorsIds,
                terms,
            }),
        });

        if (!res.ok) {
            const errorBody = await res.text();
            throw new Error(errorBody);
        }
        const data = await res.json();
        if (data.session.sessionToken) sessionStorage.setItem('sessionId', data.session.sessionToken);
        return data;
    } catch (error) {
        throw new Error(`${error.message}`);
    }
};

export const editUser = async (name, selectedSectorsIds, terms, sessionStorageId) => {
    try {
        const res = await fetch(`http://localhost:8080/api/users/${sessionStorageId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name,
                sectors: selectedSectorsIds,
                terms,
                sessionId: sessionStorage.getItem('sessionId'),
            }),
        });

        if (!res.ok) {
            const errorBody = await res.text();
            throw new Error(errorBody);
        }
        const data = await res.json();
        return data;
    } catch (error) {
        throw new Error(`${error.message}`);
    }
};

export const fetchSectors = async () => {
    try {
        const res = await fetch('http://localhost:8080/api/sectors', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!res.ok) {
            throw new Error(`Failed to fetch sectors: ${res.status} - ${res.statusText}`);
        }

        const data = await res.json();
        return data;
    } catch (error) {
        throw new Error(`${error.message}`);
    }
};

export const fetchUser = async (sessionId) => {
    try {
        const res = await fetch(`http://localhost:8080/api/users/${sessionId}`);

        if (!res.ok) {
            throw new Error(`Failed to fetch user: ${res.status} - ${res.statusText}`);
        }

        const data = await res.json();
        return data;
    } catch (error) {
        throw new Error(`${error.message}`);
    }
};
import { Button, Checkbox, FormControlLabel, TextField } from '@mui/material';
import './App.css';
import ChipPicker from './components/ChipPicker';
import { useEffect, useState } from 'react';
import { addUser, editUser, fetchUser } from './api/api';
import Alert from './components/Alert';

function App() {
  const [name, setName] = useState('');
  const [terms, setTerms] = useState(false);
  const [selectedSectorsIds, setSelectedSectorIds] = useState([]); // This is used to send the sectors to the API.
  const [selectedSectorsAPI, setSelectedSectorsAPI] = useState([]); // This is used to load the sectors from the API when editing a user.
  const [alertVisible, setAlertVisible] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [alertSeverity, setAlertSeverity] = useState('error');

  const handleChipPickerChange = (selectedIds) => {
    setSelectedSectorIds(selectedIds);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    let ids;
    if (selectedSectorsIds.length === 0) {
      ids = selectedSectorsAPI;
    } else {
      ids = selectedSectorsIds.map((sector) => sector.id);
    }

    const sessionStorageId = sessionStorage.getItem('sessionId');
    try {
      if (!sessionStorageId) {
        const user = await addUser(name, ids, terms);
        setAlertMessage('User added successfully');
      } else {
        const user = await editUser(name, ids, terms, sessionStorageId);
        setAlertMessage('User modified successfully');
      }
      setAlertSeverity('success');
      setAlertVisible(true);
    } catch (err) {
      setAlertMessage(err.message);
      setAlertSeverity('error');
      setAlertVisible(true);
    }
  };

  useEffect(() => {
    const sessionStorageId = sessionStorage.getItem('sessionId');
    if (sessionStorageId) {
      fetchUser(sessionStorageId)
        .then((user) => {
          setName(user.name);
          setSelectedSectorsAPI(user.sectors);
          setTerms(user.terms);
        })
        .catch((err) => {
          setAlertMessage(err.message);
          setAlertSeverity('error');
          setAlertVisible(true);
        });
    }
  }, []);

  return (
    <div className="App">
      <Alert open={alertVisible} onClose={() => setAlertVisible(false)} message={alertMessage} severity={alertSeverity} />
      <header className="App-header">
        <p>
          Please enter your name and pick the Sectors you are currently involved in.
        </p>
        <form onSubmit={handleSubmit}>
          <TextField id="standard-basic" required value={name} onChange={(e) => setName(e.target.value)} label="Name" variant="standard" />
          <ChipPicker onSelectChange={handleChipPickerChange} loadedSectors={selectedSectorsAPI} />
          <FormControlLabel checked={terms} required control={<Checkbox />} label="Agree to terms" onClick={() => setTerms(!terms)} style={{ alignItems: 'flex-start' }} />
          <Button type="submit" variant="outlined">Send</Button>
        </form>
      </header>
    </div >
  );
}

export default App;

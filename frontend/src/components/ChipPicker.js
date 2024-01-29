import * as React from 'react';
import { useEffect, useState } from 'react';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Chip from '@mui/material/Chip';
import { fetchSectors } from '../api/api';
import Alert from './Alert';
import MenuItemComponent from './MenuItemComponent'


const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
            width: 450,
        },
    },
};

const ChipPicker = ({ onSelectChange, loadedSectors }) => {
    const theme = useTheme();
    const [sectors, setSectors] = React.useState([]);
    const [selectedSectors, setSelectedSectors] = React.useState([]);
    const [alertVisible, setAlertVisible] = useState(false);
    const [alertMessage, setAlertMessage] = useState('');
    const [alertSeverity, setAlertSeverity] = useState('error');

    useEffect(() => {
        const getSectors = async () => {
            const sectorsFromServer = await fetchSectors();
            setSectors(sectorsFromServer);
        };
        getSectors();
    }, []);

    useEffect(() => {
        if (loadedSectors && loadedSectors.length > 0) {
            setSelectedSectors(loadedSectors);
        }
    }, [loadedSectors]);

    const handleSelect = (selectedValue) => {
        const isSectorSelected = selectedSectors.find((sector) => sector.id === selectedValue.id);
        if (isSectorSelected) {
            const newSelectedSectors = selectedSectors.filter((sector) => sector.id !== selectedValue.id);
            setSelectedSectors(newSelectedSectors);
            onSelectChange(newSelectedSectors);
        } else {
            if (selectedSectors.length >= 5) {
                setAlertMessage('You can only select up to 5 sectors');
                setAlertSeverity('error');
                setAlertVisible(true);
                return;
            }
            const newSelectedSectors = [...selectedSectors, selectedValue];
            setSelectedSectors(newSelectedSectors);
            onSelectChange(newSelectedSectors);
        }
    };

    const handleDelete = (selectedValue) => {
        const newSelectedSectors = selectedSectors.filter((sector) => sector.id !== selectedValue);
        setSelectedSectors(newSelectedSectors);
        onSelectChange(newSelectedSectors);
    };

    const renderSectors = (sectors, selectedSectors, theme, level = 0) => {
        return sectors.map((sector) => (
            <MenuItemComponent
                key={sector.name}
                sector={sector}
                level={level}
                selectedSectors={selectedSectors}
                theme={theme}
                handleSelect={handleSelect}
                renderSectors={renderSectors}
            />
        ));
    };

    return (
        <div>
            <Alert open={alertVisible} onClose={() => setAlertVisible(false)} message={alertMessage} severity={alertSeverity} />
            <FormControl sx={{ m: 1, width: 300 }} required>
                <InputLabel id="demo-multiple-chip-label">Sectors</InputLabel>
                <Select
                    labelId="demo-multiple-chip-label"
                    id="demo-multiple-chip"
                    multiple
                    value={selectedSectors.map((sector) => sector)}
                    input={<OutlinedInput id="select-multiple-chip" label="Sectors" />}
                    renderValue={(selected) => (
                        <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                            {selected.map((value) => (
                                <Chip
                                    key={value.id}
                                    label={value.name}
                                    onDelete={(event) => {
                                        event.preventDefault();
                                        handleDelete(value.id);
                                    }}
                                    onMouseDown={(event) => {
                                        event.stopPropagation();
                                    }}
                                />
                            ))}
                        </Box>
                    )}
                    MenuProps={MenuProps}
                >
                    {renderSectors(sectors, selectedSectors, theme)}
                </Select>
            </FormControl>
        </div>
    );
}

export default ChipPicker;
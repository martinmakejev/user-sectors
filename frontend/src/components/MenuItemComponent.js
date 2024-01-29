// MenuItemComponent.js
import React from 'react';
import MenuItem from '@mui/material/MenuItem';

const MenuItemComponent = ({ sector, level, selectedSectors, theme, handleSelect, renderSectors }) => {
    const hasChildren = sector.subSectors && sector.subSectors.length > 0;

    const getStyles = (name, personName, theme) => {
        return {
            fontWeight:
                personName.indexOf(name) === -1
                    ? theme.typography.fontWeightRegular
                    : theme.typography.fontWeightMedium,
        };
    }

    return (
        <div key={sector.name}>
            <MenuItem
                value={sector.id}
                onClick={() => handleSelect(sector)}
                style={{
                    marginLeft: level * 16,
                    fontWeight: getStyles(sector.name, selectedSectors, theme),
                    ...(hasChildren && { fontWeight: 'bold', opacity: 0.5 }),
                }}
                selected={selectedSectors.find((selectedSector) => selectedSector.id === sector.id)}
                disabled={hasChildren}
            >
                {sector.name}
            </MenuItem>
            {hasChildren && renderSectors(sector.subSectors, selectedSectors, theme, level + 1)}
        </div>
    );
};

export default MenuItemComponent;

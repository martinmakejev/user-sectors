import React from 'react';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';

const Alert = ({ open, onClose, message, severity }) => {
    return (
        <Snackbar open={open} autoHideDuration={6000} onClose={onClose} anchorOrigin={{ vertical: 'top', horizontal: 'right' }}>
            <MuiAlert onClose={onClose} severity={severity} sx={{ width: '100%' }} >
                {message}
            </MuiAlert>
        </Snackbar>
    );
};

export default Alert;
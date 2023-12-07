import { TableCell, TableRow, Tooltip } from '@mui/material';
import { tableCellClasses } from '@mui/material/TableCell';
import { tooltipClasses } from '@mui/material/Tooltip';
import { alpha, styled } from '@mui/material/styles';


const useStyles = (theme) => ({
  iconEnabled: {
    color: theme.palette.info.main
  },
  iconDisabled: {
    color: theme.palette.grey['600']
  },
  notResults: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    overflow: 'hidden'
  },
  notResultsDraw: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: theme.palette.primary[200],
    borderRadius: '100%',
    marginBottom: theme.spacing(3)
  },
  notResultsDrawIcon: {
    color: theme.palette.common.white
  },
  barscroll: {
    overflowY: "auto",
    height: "100%",
    '&::-webkit-scrollbar': {
      width: '16px'
    },
    '&::-webkit-scrollbar-track': {
      boxShadow: 'inset 0 0 6px rgba(0,0,0,0.00)',
      webkitBoxShadow: 'inset 0 0 6px rgba(0,0,0,0.00)'
    },
    '&::-webkit-scrollbar-thumb': {
      borderRadius: '8px',
      backgroundColor: theme.palette.primary[100],
      border: `4px solid ${theme.palette.common.white}`,
      '&:hover': {
        backgroundColor: theme.palette.primary[200]
      },
      '&:active': {
        backgroundColor: theme.palette.primary[300]
      }
    }
  }
})

export default useStyles;

export const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    // border: 0,
    color: theme.palette.grey['600']
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

export const StyledTableCellAction = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: alpha(theme.palette.primary.main, 0.05),
    fontWeight: 'bold'
  },
  [`&.${tableCellClasses.body}`]: {
    width: '154px',
    padding: '0px 16px 0px 0px'
  },
}));

export const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(n) td, &:nth-of-type(n) th': {
    // border: 0
    borderTop: 0,
    borderBottom: 0
  },
  // '&:nth-of-type(n)': {
  //   '&:hover': {
  //     transition: '.3s',
  //     background: `linear-gradient(90deg, ${alpha(theme.palette.primary.main, 1)} 0%, ${alpha(theme.palette.primary.main, 0.1)} 150%)`,
  //     cursor: 'pointer',
  //     borderRadius: theme.spacing(6)
  //   },
  //   '&:hover > td, &:hover > th': {
  //     color: theme.palette.common.white
  //   },
  //   backgroundColor: 'transparent',
  // },
  '&:nth-of-type(odd)': {
    backgroundColor: alpha(theme.palette.primary.main, 0.08),
    borderRadius: '16px'
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    // border: 0
    borderTop: 0,
    // borderBottom: 0
  },
}));

export const ToolTipStyle = styled(({ className, ...props }) => (
  <Tooltip {...props} arrow classes={{ popper: className }} />
))(({ theme }) => ({
  [`& .${tooltipClasses.arrow}`]: {
    color: theme.palette.primary.light,
  },
  [`& .${tooltipClasses.tooltip}`]: {
    borderRadius: '4px',
    backgroundColor: theme.palette.primary.light,
  },
}));

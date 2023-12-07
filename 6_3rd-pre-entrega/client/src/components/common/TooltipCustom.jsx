import React from "react";
import { styled } from '@mui/material/styles';
import { Fade, Tooltip } from "@mui/material";
import { tooltipClasses } from '@mui/material/Tooltip';
import { useTranslation } from 'react-i18next';
import HelpOutlineIcon from '@mui/icons-material/HelpOutline';


const ToolTipStyle = styled(({ className, ...props }) => (
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

export default function TooltipCustom ({ title='' }) {
  const [t, i18n] = useTranslation();
  const titleDefault = i18n.exists(title) ? t(title) : title;

  return (
    <ToolTipStyle
      arrow
      disableFocusListener
      placement="top"
      title={titleDefault}
      TransitionComponent={Fade}
      TransitionProps={{ timeout: 300 }}
    >
      <HelpOutlineIcon color='primary' sx={{ fontSize: 18 }} />
    </ToolTipStyle>
  );
};

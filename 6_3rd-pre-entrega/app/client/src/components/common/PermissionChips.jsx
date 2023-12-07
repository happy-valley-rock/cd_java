import React, { useState,  } from 'react';
import { Box, Chip } from '@mui/material';
import { useTranslation } from 'react-i18next';
import { useTheme } from '@mui/material/styles';
import DeleteForeverOutlinedIcon from '@mui/icons-material/DeleteForeverOutlined';
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import PlayArrowOutlinedIcon from '@mui/icons-material/PlayArrowOutlined';
import RemoveRedEyeOutlinedIcon from '@mui/icons-material/RemoveRedEyeOutlined';
import { red, amber, green, purple } from '@mui/material/colors';

export default function PermissionChips({
  disabled = false,
  view,
  content,
}) {
  const [t] = useTranslation();
  const theme = useTheme();

  const chipVariants = {
    isEdit: {
      color: theme.palette.primary[500],
      icon: EditOutlinedIcon,
      label: t('common.components.chip.edit')
    },
    isCreate: {
      color: amber[500],
      icon: AddOutlinedIcon,
      label: t('common.components.chip.create')
    },
    isDelete: {
      color: red[500],
      icon: DeleteForeverOutlinedIcon,
      label: t('common.components.chip.delete')
    },
    isExecute: {
      color: purple[500],
      icon: PlayArrowOutlinedIcon,
      label: t('common.components.chip.run')
    },
    isVisible: {
      color: green[500],
      icon: RemoveRedEyeOutlinedIcon,
      label: t('common.components.chip.visible')
    },
  }

  const ChipComponent = (props) => {

    const {
      name,
      id,
      variant = 'isVisible',
      disabled = false,
      value  = false
    } = props;

    const variantSelected = chipVariants[variant];
    const [isActive, setisActive] = useState(value);

    return (
      <>
        <input
          value={isActive}
          id={id}
          style={{display: 'none'}}
          onChange = { () => ({}) }
          name={name}
        />
        <Chip
          sx = {{
            backgroundColor: isActive ? (variantSelected.color) : 'gray',
            color: 'white',
          }}
          size = "small"
          icon = { <variantSelected.icon sx={{ "&&": { color: "white!important" } }} /> }
          label = { variantSelected.label }
          disabled = { disabled }
          onClick = { () => setisActive(!isActive) }
        />
      </>

    )
  }

  return (
      <Box
        sx = {{
          display: 'flex',
          flexDirection: 'row',
          gap: 2,
        }}
      >
        {Object.keys(content).map(p => (
            <React.Fragment key= { `${view}-${p}` }>
              { chipVariants[p] &&
                <ChipComponent
                  name = { `${view}-${p}` }
                  id = { `${view}-${p}` }
                  variant = { p }
                  disabled = { disabled }
                  value  = { content[p] }
                />
              }
            </React.Fragment>
        ))}
      </Box>
  );
}

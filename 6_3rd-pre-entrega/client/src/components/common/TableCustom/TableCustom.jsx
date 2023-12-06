import ExpandCircleDownOutlinedIcon from '@mui/icons-material/ExpandCircleDownOutlined';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import RefreshIcon from '@mui/icons-material/Refresh';
import RemoveShoppingCartIcon from '@mui/icons-material/RemoveShoppingCart';
import {
  Box,
  Button,
  Chip,
  Collapse,
  Fade,
  IconButton,
  Pagination,
  Skeleton,
  Table,
  TableBody,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
  Checkbox
} from '@mui/material';
import { alpha, useTheme } from '@mui/material/styles';
import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import { PermissionChips } from '~/components/common';
import useStyles, { StyledTableCell, StyledTableRow, ToolTipStyle } from './styles';


export default function TableCustom({
  columns=null,
  childrenColumns=null,
  content=[],
  title,
  loading,
  handleRefresh,
  handleNotResults=null,
  handleOnClickRow=null,
  config={},
  actions=null,
  childrenActions=null,
  showPaginator=false,
  count=10,
  page=1,
  handleChangePage,
  handleOnclickColumn=null,
  showSort=false,
  sort={
    type: 'DESC',
    key: null
  },
  setSort=null,
  enabledCheckbox=false,
  handleOnClickCheckbox=null
}) {
  const [t] = useTranslation();
  const theme = useTheme();
  const styles = useStyles(theme);
  const [selected, setSelected] = useState([]);
  const [allSelected, setAllSelected] = useState([]);

  const defaultConfig = {
    maxHeight: config?.maxHeight || '300px',
    minHeight: config?.minHeight || 'inherit',
    size: config?.size || 'medium',
    notResults: {
      icon: config?.notResults?.icon || RemoveShoppingCartIcon,
      size: config?.notResults?.size || '50vh',
      disabledIcon: config?.notResults?.disabledIcon || false,
      title:
        config?.notResults?.title || 'common.components.table.titleNotResults',
      description:
        config?.notResults?.description ||
        'common.components.table.descriptionNotResults',
      buttonLabel:
        config?.notResults?.buttonLabel || 'common.components.table.buttonNew',
    },
  };

  const setHandleOnClickRow = (item) => {
    if(handleOnClickRow) return () => handleOnClickRow(item);
  }

  const isSelected = (name) => selected.indexOf(name) !== -1;

  function Cell({ item, col, index }) {
    const cols = col.key.split('.');
    let objectKey = item;

    for (var i = 0; i < cols.length; i++) {
      objectKey = objectKey[`${cols[i]}`];
    }

    return (
      <>
        { col?.type === 'chip' && col?.options ?
          (
            <Chip label={ t(objectKey) } sx={{
              backgroundColor: col.options[t(objectKey)],
              color: 'white'
            }}/>
          ) : col?.type === 'chip' && !col.options?
          (
            <Chip label={ t(objectKey)} color='primary'/>
          )
          : col?.type === 'permissions' ?
          (
            <PermissionChips
              view    = { objectKey.view }
              content = { objectKey }
            />
          )
          :
          (
            <>{ objectKey }</>
          )
        }
      </>
    );
  }

  function CellCheckbox({ item, index }) {
    const isItemSelected = isSelected(index);

    return (
      <>
        <Checkbox
          color="primary"
          checked={isItemSelected}
          size="small"
          sx={{ p: 0 }}
        />
      </>
    );
  }

  function ChildrenRow({ open, children }) {
    return (
      <StyledTableRow>
        <StyledTableCell
          size={defaultConfig.size}
          sx={{
            padding: 0,
            backgroundColor: 'white'}}
          colSpan={columns.length + 1}
        >
          <Collapse
            in={open}
            timeout="auto"
            unmountOnExit
            sx={{
              paddingTop: 2, paddingLeft: 2, marginLeft: 3,
              borderLeft: `solid 2px ${theme.palette.primary[100]}`
             }}
          >
            <Table aria-label="customized table children" stickyHeader>
              <TableBody >
                { children.map((item, index) => (
                  <Row
                    key={index}
                    item={item}
                    columns={childrenColumns}
                    actions={childrenActions}
                  />
                ))}
              </TableBody>
            </Table>
          </Collapse>
        </StyledTableCell>
      </StyledTableRow>
    );
  }

  function Row({ item, columns, actions, index }) {
    const children = item?.children || null;
    const [open, setOpen] = useState(false);

    return (
      <>
        <StyledTableRow
          sx={ handleOnClickRow && {
            '&:hover': {
              transition: '.3s',
              background: `linear-gradient(90deg, ${alpha(theme.palette.primary.main, 1)} 0%, ${alpha(theme.palette.primary.main, 0.1)} 150%)`,
              cursor: 'pointer',
              borderRadius: theme.spacing(6)
            },
            '&:hover > td, &:hover > th': {
              color: theme.palette.common.white
            }
          }}
        >
          { enabledCheckbox &&
            <StyledTableCell
              onClick={() => handleOnClickCheckbox(item)}
              colSpan={0}
              size="small"
              component="th"
              scope="row"
              sx={{ pr: 0 }}
            >
              <CellCheckbox
                item={item}
                index={index}
              />
            </StyledTableCell>
          }

          { columns.map((col, index) => (
            <StyledTableCell
              onClick={setHandleOnClickRow(item)}
              key={index}
              colSpan={col?.colSpan ? item[col.colSpan] : 1}
              size={defaultConfig.size}
              component="th"
              scope="row"
              sx={ col?.sx ? col.sx : {} }
            >
              <Cell
                item={item}
                col={col}
              />
            </StyledTableCell>
          ))}
          { actions &&
            <StyledTableCell
              sx={{ p: 0 }}
              size={defaultConfig.size}
              key={'action-row-col'}
              component="th"
              scope="row"
              align="right"
            >
              <Box
                component='div'
                sx={{
                mr: 1
              }}>
                { actions.map((action, index) => (
                  <ToolTipStyle
                    arrow
                    key={index}
                    disableFocusListener
                    placement="top-start"
                    title={action.label}
                    TransitionComponent={Fade}
                    TransitionProps={{ timeout: 300 }}
                    enterDelay={700}
                  >
                    { (action.handle === 'children' && children && children.length > 0) ?
                      (
                        <IconButton
                          aria-label="expand row"
                          size="small"
                          onClick={() => setOpen(!open)}
                        >
                          { open ?
                            <ExpandCircleDownOutlinedIcon sx={{ transform: 'rotateZ(180deg)'}} color='primary' />
                            : <ExpandCircleDownOutlinedIcon color='primary' />
                          }
                        </IconButton>
                      )
                      :(
                        <IconButton
                          onClick={() => action.handle(item, index)}
                          aria-label={`action-${index}`}
                          size="small"
                        >
                          {action.icon}
                        </IconButton>
                      )
                    }
                  </ToolTipStyle>
                ))}
              </Box>
            </StyledTableCell>
          }
        </StyledTableRow>

        { (children && children.length > 0) &&
          <ChildrenRow open={open} children={children} />
        }
      </>
    );
  }

  function ColumnButton({col, onClick}) {

    function handleOnClick(event) {
      event.preventDefault();
      if (!setSort) return;
      let type = 'DESC'
      if(sort.key === col.key) {
        type = (sort.type === 'ASC') ? 'DESC' : 'ASC';
      }

      const sortType =  { type, key: col.key }
      setSort(sortType)
      onClick(sortType);
    }

    function getStatus() {
      let style = styles.iconDisabled;
      let transform = 'rotateX(0deg)';

      if (sort.key === col.key) {
        style = styles.iconEnabled
        transform = (sort.type === 'ASC') ? 'rotateX(180deg)' : 'rotateX(0deg)';
      }

      return { ...style, transform, transition: '.3s' }
    }

    return (
      <IconButton
        size="small"
        onClick={handleOnClick}
      >
        <KeyboardArrowDownIcon
          fontSize="medium"
          sx={getStatus()}
        />
      </IconButton>
    );
  }

  return (
    <>
      { loading ? (
        <Skeleton height="50vh" variant="rounded" animation="wave" />
      ) : (
        <>
          <Box>
            <Box sx={{ display: 'flex', flexDirection: 'row', gap: 1 }}>
              <Typography variant="h6" id="titleList">
                {t(title)}
              </Typography>
              { handleRefresh && (
                <IconButton
                  aria-label="refresh"
                  size="small"
                  onClick={handleRefresh}
                >
                  <RefreshIcon
                    fontSize="medium"
                    sx={styles.iconEnabled}
                  />
                </IconButton>
              )}
            </Box>
          </Box>
          { (content?.length === 0) ? (
            <Box sx={{
              ...styles.notResults,
              maxHeight: defaultConfig.maxHeight,
              height: defaultConfig.notResults.size
            }}
            >
              <Box
                sx={{
                  ...styles.notResultsDraw,
                  height: config?.size === 'medium' ? '12rem' : '10rem',
                  width: config?.size === 'medium' ? '12rem' : '10rem',
                  display: defaultConfig.notResults.disabledIcon ? 'none' : 'flex'
                }}
              >
                <defaultConfig.notResults.icon
                  sx={{
                    ...styles.notResultsDrawIcon,
                    fontSize: config?.size === 'medium' ? '9rem' : '7rem',
                  }}
                />
              </Box>
              <Typography variant="h5" id="titleList">
                {t(defaultConfig.notResults.title)}
              </Typography>
              { config?.size === 'medium' &&
                <>
                  <Typography variant="subtitle1" id="titleList" color="gray">
                    {t(defaultConfig.notResults.description)}
                  </Typography>
                  { handleNotResults && (
                    <Button onClick={handleNotResults} variant="text" size="large">
                      {t(defaultConfig.notResults.buttonLabel)}
                    </Button>
                  )}
                </>
              }
            </Box>
          ) : content && (
            <>
              <TableContainer
                component="div"
                id="table"
                aria-label="table"
                sx={{
                  ...styles.scrollbar,
                  maxHeight: defaultConfig.maxHeight, minHeight: defaultConfig.minHeight
                }}
              >
                <Table aria-label="customized table" stickyHeader>
                  <TableHead>
                    <TableRow>
                      {/* enabledCheckbox &&
                        <StyledTableCell
                          onClick={handleOnClickCheckboxAll(content)}
                          colSpan={0}
                          size="small"
                          component="th"
                          scope="row"
                          sx={{ pr: 0 }}
                        >
                          <CellCheckbox
                            item={item}
                            index={index}
                          />
                        </StyledTableCell>
                      */}
                      { columns.map((col, index) => (
                        <React.Fragment key={index}>
                          <StyledTableCell
                            size={defaultConfig.size}
                          >
                            {col.label}
                            { showSort &&
                              <ColumnButton
                                col={col}
                                onClick={handleOnclickColumn}
                              />
                            }
                          </StyledTableCell>
                        </React.Fragment>
                      ))}
                      { actions &&
                        <StyledTableCell size={defaultConfig.size}>{''}</StyledTableCell>
                      }
                    </TableRow>
                  </TableHead>
                  <TableBody >
                    { content.map((item, index) => (
                      <Row
                        key={index}
                        item={item}
                        columns={columns}
                        actions={actions}
                        index={index}
                      />
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
              { showPaginator && (
                <Box sx={{ display: 'flex', flexDirection: 'row-reverse' }}>
                  <Pagination
                    count={count}
                    page={page}
                    onChange={handleChangePage}
                    sx = {{ mt: 1 }}
                  />
                </Box>
              )}
            </>
          )}
        </>
      )}
    </>
  );
}

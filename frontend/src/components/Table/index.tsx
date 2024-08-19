import {
  Table,
  TableContainer,
  TableHeadProps,
  TableRowProps,
  Tbody,
  Td,
  Th,
  Thead,
  Tr,
} from "@chakra-ui/react";

interface ITableHead extends TableHeadProps {
  headProps: {
    label: string | React.ReactNode;
  }[];
  children: React.ReactNode[] | React.ReactNode;
}

interface ITableBody extends TableRowProps {
  colsBody: {
    ceil: any;
  }[];
}

export const InfoTable = ({ headProps, children, ...rest }: ITableHead) => (
  <TableContainer maxWidth="100%">
    <Table variant="simple" minWidth="100%">
      <Thead {...rest}>
        {headProps.map(({ label }) => (
          <Th>{label}</Th>
        ))}
      </Thead>
      <Tbody>{children}</Tbody>
    </Table>
  </TableContainer>
);

export const InfoTableContent = ({ colsBody, ...rest }: ITableBody) => (
  <Tr {...rest} hover>
    {colsBody.map(({ ceil }, i) => (
      <Td key={i}>{ceil}</Td>
    ))}
  </Tr>
);

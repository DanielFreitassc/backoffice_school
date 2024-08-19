import {
  FormControl,
  FormErrorMessage,
  Select,
  SelectProps,
  Text,
} from "@chakra-ui/react";

interface ISelectprops extends SelectProps {
  label: string;
  error?: string;
  children: React.ReactNode;
}

export const FormSelect = ({
  label,
  error,
  children,
  ...rest
}: ISelectprops) => (
  <FormControl isInvalid={!!error}>
    {label && <Text>{label}</Text>}
    <Select {...rest}>{children}</Select>
    <FormErrorMessage>{error && error}</FormErrorMessage>
  </FormControl>
);

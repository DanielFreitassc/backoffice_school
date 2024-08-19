import { Text, TextProps } from "@chakra-ui/react";

interface ITitleProps extends TextProps {
  label: string;
}

export const Title = ({ label, ...rest }: ITitleProps) => (
  <Text as="h3" fontSize="4xl" fontWeight="semibold" {...rest}>
    {label}
  </Text>
);

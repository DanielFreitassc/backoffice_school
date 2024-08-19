import {
  Button,
  Popover,
  PopoverArrow,
  PopoverBody,
  PopoverCloseButton,
  PopoverContent,
  PopoverHeader,
  PopoverTrigger,
  Portal,
} from "@chakra-ui/react";
import { Trash } from "@phosphor-icons/react";

interface IDeletePopover {
  onClick(): void;
  section: string;
}

export const PopoverDelete = ({ onClick, section }: IDeletePopover) => (
  <Popover>
    <PopoverTrigger>
      <Button variant="none">
        <Trash color="red" size={22} />
      </Button>
    </PopoverTrigger>
    <Portal>
      <PopoverContent>
        <PopoverArrow />
        <PopoverHeader>Excluir</PopoverHeader>
        <PopoverCloseButton />
        <PopoverBody>{`Você realmente apagar este ${section}?`}</PopoverBody>
        <PopoverBody>
          <Button colorScheme="red" onClick={() => onClick()}>
            Excluir
          </Button>
        </PopoverBody>
      </PopoverContent>
    </Portal>
  </Popover>
);

import * as Styles from "./styles";

interface IIconButton {
  icon: React.ReactNode;
  label: string;
  onClick: () => void;
}

export const IconButton = ({ icon, label, onClick }: IIconButton) => {
  return (
    <Styles.Container onClick={() => onClick()}>
      {label && <span>{label}</span>}
      {icon}
    </Styles.Container>
  );
};

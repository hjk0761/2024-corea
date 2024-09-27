import { useNavigate } from "react-router-dom";
import useMutateRoom from "@/hooks/mutations/useMutateRoom";
import Button from "@/components/common/button/Button";
import { RoomInfo } from "@/@types/roomInfo";

interface RoomCardModalButtonProps {
  roomInfo: RoomInfo;
}

const RoomCardModalButton = ({ roomInfo }: RoomCardModalButtonProps) => {
  const navigate = useNavigate();
  const { postParticipateInMutation } = useMutateRoom();
  const isLoggedIn = !!localStorage.getItem("accessToken");

  const handleParticipateRoomClick = () => {
    postParticipateInMutation.mutate(roomInfo.id, {
      onSuccess: () => navigate(`/rooms/${roomInfo.id}`),
    });
  };

  if (!isLoggedIn) {
    return (
      <Button variant="disable" size="medium" disabled>
        로그인 후 참여 가능
      </Button>
    );
  }

  if (roomInfo.participationStatus !== "NOT_PARTICIPATED") {
    return (
      <Button variant="disable" size="small" disabled>
        참여중
      </Button>
    );
  }

  if (roomInfo.roomStatus !== "OPEN") {
    return (
      <Button variant="disable" size="small" disabled>
        모집 완료
      </Button>
    );
  }

  return (
    <Button variant="primary" size="small" onClick={handleParticipateRoomClick}>
      참여하기
    </Button>
  );
};

export default RoomCardModalButton;

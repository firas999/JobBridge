<?php

namespace App\Entity;

use App\Repository\ParticipantRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ParticipantRepository::class)
 */
class Participant
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $IdEvenement;

    /**
     * @ORM\Column(type="integer")
     */
    private $IdParticipant;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $TypeParticipant;

    /**
     * @ORM\ManyToOne(targetEntity=Evenement::class, inversedBy="participants")
     * @ORM\JoinColumn(nullable=false)
     */
    private $IDevenement;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdEvenement(): ?int
    {
        return $this->IdEvenement;
    }

    public function setIdEvenement(int $IdEvenement): self
    {
        $this->IdEvenement = $IdEvenement;

        return $this;
    }

    public function getIdParticipant(): ?int
    {
        return $this->IdParticipant;
    }

    public function setIdParticipant(int $IdParticipant): self
    {
        $this->IdParticipant = $IdParticipant;

        return $this;
    }

    public function getTypeParticipant(): ?string
    {
        return $this->TypeParticipant;
    }

    public function setTypeParticipant(string $TypeParticipant): self
    {
        $this->TypeParticipant = $TypeParticipant;

        return $this;
    }
}

<?php

namespace App\Entity;

use App\Repository\OffreStageRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=OffreStageRepository::class)
 */
class OffreStage
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $TypeStage;

    /**
     * @ORM\Column(type="dateinterval")
     */
    private $Duree;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Exigence;


    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="offreStages")
     * @ORM\JoinColumn(nullable=false)
     */
    private $IdEntreprise;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTypeStage(): ?string
    {
        return $this->TypeStage;
    }

    public function setTypeStage(string $TypeStage): self
    {
        $this->TypeStage = $TypeStage;

        return $this;
    }

    public function getDuree(): ?\DateInterval
    {
        return $this->Duree;
    }

    public function setDuree(\DateInterval $Duree): self
    {
        $this->Duree = $Duree;

        return $this;
    }

    public function getExigence(): ?string
    {
        return $this->Exigence;
    }

    public function setExigence(string $Exigence): self
    {
        $this->Exigence = $Exigence;

        return $this;
    }

    public function getIdSociete(): ?int
    {
        return $this->IdSociete;
    }

    public function setIdSociete(int $IdSociete): self
    {
        $this->IdSociete = $IdSociete;

        return $this;
    }

    public function getIdEntreprise(): ?Entreprise
    {
        return $this->IdEntreprise;
    }

    public function setIdEntreprise(?Entreprise $IdEntreprise): self
    {
        $this->IdEntreprise = $IdEntreprise;

        return $this;
    }
}

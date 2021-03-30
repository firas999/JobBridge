<?php

namespace App\Entity;

use App\Repository\CertificationRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=CertificationRepository::class)
 */
class Certification
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
    private $Type;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Date is required")
     */
    private $DatePassage;

    /**
     * @ORM\Column(type="integer")
     * @Assert\Positive
     * @Assert\NotBlank(message="Price is required")
     */
    private $Prix;

    /**
     * @ORM\Column(type="text")
     * @Assert\NotBlank(message="Description is required")
     * @Assert\Length(
     *      min = 10,    
     *      minMessage = "Description must be at least {{ limit }} characters long",
     * )
     */
    private $Description;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Name is required")
     */
    private $Nom;

    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="yes")
     * @ORM\JoinColumn(nullable=false)
     * @Assert\NotBlank(message="Entreprise is required")
     */
    private $Entreprise;

    /**
     * @ORM\OneToMany(targetEntity=DemandeCertification::class, mappedBy="Certification", orphanRemoval=true)
     */
    private $yes;



    protected $captchaCode;
    
    public function getCaptchaCode()
    {
      return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
      $this->captchaCode = $captchaCode;
    }


    public function __construct()
    {
        $this->yes = new ArrayCollection();
    }

  


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->Type;
    }

    public function setType(string $Type): self
    {
        $this->Type = $Type;

        return $this;
    }

    public function getDatePassage(): ?\DateTimeInterface
    {
        return $this->DatePassage;
    }

    public function setDatePassage(\DateTimeInterface $DatePassage): self
    {
        $this->DatePassage = $DatePassage;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->Prix;
    }

    public function setPrix(int $Prix): self
    {
        $this->Prix = $Prix;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->Nom;
    }

    public function setNom(string $Nom): self
    {
        $this->Nom = $Nom;

        return $this;
    }

    public function getIdSociete(): ?int
    {
        return $this->idSociete;
    }

    public function setIdSociete(int $idSociete): self
    {
        $this->idSociete = $idSociete;

        return $this;
    }

    public function getEntreprise(): ?Entreprise
    {
        return $this->Entreprise;
    }

    public function setEntreprise(?Entreprise $Entreprise): self
    {
        $this->Entreprise = $Entreprise;

        return $this;
    }






    /**
     * @return Collection|DemandeCertification[]
     */
    public function getYes(): Collection
    {
        return $this->yes;
    }

    public function addYe(DemandeCertification $ye): self
    {
        if (!$this->yes->contains($ye)) {
            $this->yes[] = $ye;
            $ye->setCertification($this);
        }

        return $this;
    }

    public function removeYe(DemandeCertification $ye): self
    {
        if ($this->yes->removeElement($ye)) {
            // set the owning side to null (unless already changed)
            if ($ye->getCertification() === $this) {
                $ye->setCertification(null);
            }
        }

        return $this;
    }


}

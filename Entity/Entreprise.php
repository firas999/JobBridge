<?php

namespace App\Entity;

use App\Repository\EntrepriseRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=EntrepriseRepository::class)
 */
class Entreprise
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
    private $Secteur;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $SiteWeb;

    /**
     * @ORM\Column(type="integer")
     */
    private $Taille;

    /**
     * @ORM\Column(type="bigint")
     */
    private $Telephone;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $MatriculeFiscal;

    /**
     * @ORM\OneToMany(targetEntity=Article::class, mappedBy="entreprise", orphanRemoval=true)
     */
    private $Articles;

    /**
     * @ORM\OneToMany(targetEntity=Formation::class, mappedBy="entreprise", orphanRemoval=true)
     */
    private $Formations;

  /**
     * @ORM\OneToMany(targetEntity=OffreEmploi::class, mappedBy="entreprise", orphanRemoval=true)
     */
    private $OffresEmploi;

    /**
     * @ORM\OneToMany(targetEntity=CandidatEmploi::class, mappedBy="IdEntreprise", )
     */
    private $candidatEmploi;

  /**
     * @ORM\OneToMany(targetEntity=OffreStage::class, mappedBy="IdEntreprise", orphanRemoval=true)
     */
    private $offreStages;

    /**
     * @ORM\OneToMany(targetEntity=CandidatStage::class, mappedBy="IdEntreprise", orphanRemoval=true)
     */

    private $candidatStage;

    /**
     * @ORM\OneToMany(targetEntity=Certification::class, mappedBy="IdEntreprise", orphanRemoval=true)
     */
    private $certifications;

    /**
     * @ORM\OneToMany(targetEntity=Certification::class, mappedBy="Entreprise", orphanRemoval=true)
     */
    private $yes;

    public function __construct()
    {
        $this->Articles = new ArrayCollection();
        $this->Formations = new ArrayCollection();
        $this->OffresEmploi = new ArrayCollection();
        $this->offreStages = new ArrayCollection();
        $this->certifications = new ArrayCollection();
        $this->yes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getSecteur(): ?string
    {
        return $this->Secteur;
    }

    public function setSecteur(string $Secteur): self
    {
        $this->Secteur = $Secteur;

        return $this;
    }

    public function getSiteWeb(): ?string
    {
        return $this->SiteWeb;
    }

    public function setSiteWeb(string $SiteWeb): self
    {
        $this->SiteWeb = $SiteWeb;

        return $this;
    }

    public function getTaille(): ?int
    {
        return $this->Taille;
    }

    public function setTaille(int $Taille): self
    {
        $this->Taille = $Taille;

        return $this;
    }

    public function getTelephone(): ?string
    {
        return $this->Telephone;
    }

    public function setTelephone(string $Telephone): self
    {
        $this->Telephone = $Telephone;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

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

    public function getMatriculeFiscal(): ?string
    {
        return $this->MatriculeFiscal;
    }

    public function setMatriculeFiscal(string $MatriculeFiscal): self
    {
        $this->MatriculeFiscal = $MatriculeFiscal;

        return $this;
    }

    /**
     * @return Collection|Article[]
     */
    public function getArticles(): Collection
    {
        return $this->Articles;
    }

    public function addArticle(Article $article): self
    {
        if (!$this->Articles->contains($article)) {
            $this->Articles[] = $article;
            $article->setEntreprise($this);
        }

        return $this;
    }

    public function removeArticle(Article $article): self
    {
        if ($this->Articles->removeElement($article)) {
            // set the owning side to null (unless already changed)
            if ($article->getEntreprise() === $this) {
                $article->setEntreprise(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Formation[]
     */
    public function getFormations(): Collection
    {
        return $this->Formations;
    }

    public function addFormation(Formation $formation): self
    {
        if (!$this->Formations->contains($formation)) {
            $this->Formations[] = $formation;
            $formation->setEntreprise($this);
        }

        return $this;
    }

    public function removeFormation(Formation $formation): self
    {
        if ($this->Formations->removeElement($formation)) {
            // set the owning side to null (unless already changed)
            if ($formation->getEntreprise() === $this) {
                $formation->setEntreprise(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|OffreEmploi[]
     */
    public function getOffresEmploi(): Collection
    {
        return $this->OffresEmploi;
    }

    public function addOffresEmploi(OffreEmploi $offresEmploi): self
    {
        if (!$this->OffresEmploi->contains($offresEmploi)) {
            $this->OffresEmploi[] = $offresEmploi;
            $offresEmploi->setEntreprise($this);
        }

        return $this;
    }

    public function removeOffresEmploi(OffreEmploi $offresEmploi): self
    {
        if ($this->OffresEmploi->removeElement($offresEmploi)) {
            // set the owning side to null (unless already changed)
            if ($offresEmploi->getEntreprise() === $this) {
                $offresEmploi->setEntreprise(null);
            }
        }

        return $this;
    }

    public function getCandidatEmploi(): ?CandidatEmploi
    {
        return $this->candidatEmploi;
    }

    public function setCandidatEmploi(CandidatEmploi $candidatEmploi): self
    {
        // set the owning side of the relation if necessary
        if ($candidatEmploi->getIDEntreprise() !== $this) {
            $candidatEmploi->setIDEntreprise($this);
        }

        $this->candidatEmploi = $candidatEmploi;

        return $this;
    }

    /**
     * @return Collection|OffreStage[]
     */
    public function getOffreStages(): Collection
    {
        return $this->offreStages;
    }

    public function addOffreStage(OffreStage $offreStage): self
    {
        if (!$this->offreStages->contains($offreStage)) {
            $this->offreStages[] = $offreStage;
            $offreStage->setIdEntreprise($this);
        }

        return $this;
    }

    public function removeOffreStage(OffreStage $offreStage): self
    {
        if ($this->offreStages->removeElement($offreStage)) {
            // set the owning side to null (unless already changed)
            if ($offreStage->getIdEntreprise() === $this) {
                $offreStage->setIdEntreprise(null);
            }
        }

        return $this;
    }

    public function getCandidatStage(): ?CandidatStage
    {
        return $this->candidatStage;
    }

    public function setCandidatStage(CandidatStage $candidatStage): self
    {
        // set the owning side of the relation if necessary
        if ($candidatStage->getIDEntreprise() !== $this) {
            $candidatStage->setIDEntreprise($this);
        }

        $this->candidatStage = $candidatStage;

        return $this;
    }

    /**
     * @return Collection|Certification[]
     */
    public function getCertifications(): Collection
    {
        return $this->certifications;
    }

    public function addCertification(Certification $certification): self
    {
        if (!$this->certifications->contains($certification)) {
            $this->certifications[] = $certification;
            $certification->setIdEntreprise($this);
        }

        return $this;
    }

    public function removeCertification(Certification $certification): self
    {
        if ($this->certifications->removeElement($certification)) {
            // set the owning side to null (unless already changed)
            if ($certification->getIdEntreprise() === $this) {
                $certification->setIdEntreprise(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Certification[]
     */
    public function getYes(): Collection
    {
        return $this->yes;
    }

    public function addYe(Certification $ye): self
    {
        if (!$this->yes->contains($ye)) {
            $this->yes[] = $ye;
            $ye->setEntreprise($this);
        }

        return $this;
    }

    public function removeYe(Certification $ye): self
    {
        if ($this->yes->removeElement($ye)) {
            // set the owning side to null (unless already changed)
            if ($ye->getEntreprise() === $this) {
                $ye->setEntreprise(null);
            }
        }

        return $this;
    }
    public function __toString(){
        $StringId=(string)$this->id;
        return $StringId;
    }
}
